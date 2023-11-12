package marvinrodr.springboot_ddd.skeleton.sample.infrastructure.persistence;

import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.SampleMother;
import marvinrodr.springboot_ddd.skeleton.sample.domain.repository.SampleRepository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
class HibernateSampleRepositoryShouldTestCase extends SampleInfrastructureModuleTestCase {

    HibernateSampleRepositoryShouldTestCase(@Autowired SampleRepository repository) {
        super(repository);
    }

    @Test
    void save_a_sample() {
        Sample sample = SampleMother.random();

        this.repository.createOrUpdate(sample);
    }
}
