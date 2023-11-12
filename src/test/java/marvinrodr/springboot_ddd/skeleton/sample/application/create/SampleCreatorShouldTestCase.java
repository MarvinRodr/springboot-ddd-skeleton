package marvinrodr.springboot_ddd.skeleton.sample.application.create;

import marvinrodr.springboot_ddd.skeleton.sample.SamplesModuleUnitTestCase;
import marvinrodr.springboot_ddd.skeleton.sample.application.SampleRequest;
import marvinrodr.springboot_ddd.skeleton.sample.domain.SampleCreatedDomainEventMother;
import marvinrodr.springboot_ddd.skeleton.sample.domain.event.SampleCreatedDomainEvent;
import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.SampleMother;
import marvinrodr.springboot_ddd.skeleton.shared.domain.transaction_handler.TransactionHandler;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

final class SampleCreatorShouldTestCase extends SamplesModuleUnitTestCase {

    private final SampleCreator sampleCreator;

    private final TransactionHandler transactionHandler;

    SampleCreatorShouldTestCase() {
        transactionHandler = mock(TransactionHandler.class);
        sampleCreator = new SampleCreator(
                this.repository,
                this.eventBus,
                this.transactionHandler
        );
    }

    @Test
    void create_a_valid_sample() {
        final Sample randomSample = SampleMother.random();
        final SampleRequest randomRequest = SampleRequest.of(randomSample.id().value());
        final SampleCreatedDomainEvent domainEvent = SampleCreatedDomainEventMother.fromSample(randomSample);

        doNothing().when(transactionHandler).runInNewTransaction(any(Runnable.class));

        sampleCreator.create(randomRequest);

        this.shouldHaveCreated(randomSample);
        this.shouldHavePublished(domainEvent);
    }

}
