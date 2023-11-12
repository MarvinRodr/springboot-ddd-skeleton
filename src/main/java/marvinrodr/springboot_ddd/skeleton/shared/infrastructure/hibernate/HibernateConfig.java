package marvinrodr.springboot_ddd.skeleton.shared.infrastructure.hibernate;

import marvinrodr.springboot_ddd.skeleton.App;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.File;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories
public class HibernateConfig {

    @Bean(name = "entityManagerFactory")
    public LocalSessionFactoryBean localSessionFactoryBean(DataSource dataSource, Environment env) {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        List<Resource> mappingFiles = searchMappingFiles();

        sessionFactoryBean.setMappingLocations(mappingFiles.toArray(new Resource[0]));
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setHibernateProperties(hibernateProperties(env));

        return sessionFactoryBean;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());

        return transactionManager;
    }

    private Properties hibernateProperties(Environment env) {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(AvailableSettings.HBM2DDL_AUTO, env.getProperty("spring.jpa.hibernate.ddl-auto"));
        hibernateProperties.put(AvailableSettings.SHOW_SQL, env.getProperty("spring.jpa.show-sql"));
        hibernateProperties.put(AvailableSettings.DIALECT, env.getProperty("spring.database-platform"));

        return hibernateProperties;
    }

    private List<Resource> searchMappingFiles() {
        List<String> modules = subdirectories();
        List<String> goodPaths = new ArrayList<>();

        for (String module : modules) {
            String[] files = mappingFilesIn(module + "/infrastructure/persistence/hibernate/mapping");

            for (String file : files) {
                goodPaths.add(module + "/infrastructure/persistence/hibernate/mapping/" + file);
            }
        }

        return goodPaths.stream().map(FileSystemResource::new).collect(Collectors.toList());
    }

    private String[] mappingFilesIn(String path) {
        String[] files = new File(path).list((current, name) -> new File(current, name).getName().contains(".hbm.xml"));

        if (null == files) {
            return new String[0];
        }

        return files;
    }

    private List<String> subdirectories() {
        String path = Paths.get("./src/main/java/" + App.PACKAGE_BASE.replaceAll("\\.", "/") + "/").toAbsolutePath().toString();

        String[] files = new File(path).list((current, name) -> new File(current, name).isDirectory());

        if (null == files) {
            return Collections.emptyList();
        }

        return Arrays.stream(files).map(file -> path + "/" + file).collect(Collectors.toList());
    }
}
