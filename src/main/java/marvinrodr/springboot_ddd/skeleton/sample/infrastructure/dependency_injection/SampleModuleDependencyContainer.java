package marvinrodr.springboot_ddd.skeleton.sample.infrastructure.dependency_injection;

import marvinrodr.springboot_ddd.skeleton.sample.application.create.OnSampleCreatedEvent;
import marvinrodr.springboot_ddd.skeleton.sample.application.create.SampleCreator;
import marvinrodr.springboot_ddd.skeleton.sample.domain.repository.SampleRepository;
import marvinrodr.springboot_ddd.skeleton.sample.infrastructure.persistence.hibernate.repository.HibernateSampleRepository;
import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.EventBus;
import marvinrodr.springboot_ddd.skeleton.shared.domain.log.Logger;
import marvinrodr.springboot_ddd.skeleton.shared.domain.transaction_handler.TransactionHandler;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleModuleDependencyContainer {

    @Bean
    public SampleRepository sampleRepository(final EntityManager entityManager) {
        return new HibernateSampleRepository(entityManager);
    }

    @Bean
    public SampleCreator sampleCreator(
            final SampleRepository sampleRepository,
            final EventBus eventBus,
            final TransactionHandler transactionHandler
    ) {
        return new SampleCreator(sampleRepository, eventBus, transactionHandler);
    }

    @Bean
    public OnSampleCreatedEvent onSampleCreatedEvent(final Logger logger) {
        return new OnSampleCreatedEvent(logger);
    }
}

