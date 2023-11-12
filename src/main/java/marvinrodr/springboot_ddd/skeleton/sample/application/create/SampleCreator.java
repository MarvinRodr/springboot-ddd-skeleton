package marvinrodr.springboot_ddd.skeleton.sample.application.create;

import marvinrodr.springboot_ddd.skeleton.sample.application.SampleRequest;
import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.model.SampleId;
import marvinrodr.springboot_ddd.skeleton.sample.domain.repository.SampleRepository;
import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.EventBus;
import marvinrodr.springboot_ddd.skeleton.shared.domain.transaction_handler.TransactionHandler;

public final class SampleCreator {

    private final SampleRepository sampleRepository;
    private final EventBus eventBus;
    private final TransactionHandler transactionHandler;

    public SampleCreator(
            final SampleRepository sampleRepository,
            final EventBus eventBus,
            final TransactionHandler transactionHandler
    ) {
        this.sampleRepository = sampleRepository;
        this.eventBus = eventBus;
        this.transactionHandler = transactionHandler;
    }

    public void create(final SampleRequest request) {
        final SampleId sampleId = SampleId.random();

        final Sample sample = Sample.create(sampleId);

        sampleRepository.createOrUpdate(sample);
        eventBus.publish(sample.pullDomainEvents());

        // TODO: Investigate about how to test with mockito what happened inside a runnable callback
        //  transactionHandler.runInNewTransaction(() -> {});
    }

}
