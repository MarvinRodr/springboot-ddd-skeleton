package marvinrodr.springboot_ddd.skeleton.sample.infrastructure.persistence.hibernate.repository;

import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.repository.SampleRepository;
import marvinrodr.springboot_ddd.skeleton.shared.domain.Service;
import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.hibernate.HibernateRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

@Service
public class HibernateSampleRepository extends HibernateRepository<Sample> implements SampleRepository {

    public HibernateSampleRepository(final EntityManager entityManager) {
        super(entityManager, Sample.class);
    }

    public void createOrUpdate(final Sample sample) {
        Optional<Sample> possibleEntity = this.getById(sample.id());

        if (possibleEntity.isPresent()) {
            this.update(possibleEntity.get());
        } else this.save(sample);
    }

}
