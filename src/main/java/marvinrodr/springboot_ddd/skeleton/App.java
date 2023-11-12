package marvinrodr.springboot_ddd.skeleton;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = App.PACKAGE_BASE)
public class App {

    public static final String PACKAGE_BASE = "marvinrodr.springboot_ddd.skeleton";

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

}
