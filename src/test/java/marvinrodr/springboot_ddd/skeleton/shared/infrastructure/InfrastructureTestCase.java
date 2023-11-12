package marvinrodr.springboot_ddd.skeleton.shared.infrastructure;

import marvinrodr.springboot_ddd.skeleton.App;
import marvinrodr.springboot_ddd.skeleton.shared.domain.repository.GenericRepository;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = App.class)
public abstract class InfrastructureTestCase<T, R extends GenericRepository<T>> {

    protected final R repository;

    protected InfrastructureTestCase(final R repository) {
        this.repository = repository;
    }
}
