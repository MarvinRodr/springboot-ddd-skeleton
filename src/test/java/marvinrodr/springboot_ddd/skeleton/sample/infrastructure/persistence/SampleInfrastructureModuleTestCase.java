package marvinrodr.springboot_ddd.skeleton.sample.infrastructure.persistence;

import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.repository.SampleRepository;
import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.InfrastructureTestCase;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class SampleInfrastructureModuleTestCase extends InfrastructureTestCase<Sample, SampleRepository> {

    SampleInfrastructureModuleTestCase (@Autowired SampleRepository repository) {
        super(repository);
    }
}
