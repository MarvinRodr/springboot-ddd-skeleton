package marvinrodr.springboot_ddd.skeleton.sample.application.create;

import marvinrodr.springboot_ddd.skeleton.sample.domain.event.SampleCreatedDomainEvent;
import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.DomainEventSubscriber;
import marvinrodr.springboot_ddd.skeleton.shared.domain.log.Logger;

@DomainEventSubscriber({SampleCreatedDomainEvent.class})
public final class OnSampleCreatedEvent {

    private final Logger logger;

    public OnSampleCreatedEvent(final Logger logger) {
        this.logger = logger;
    }

    @SuppressWarnings("unused")
    public void on(final SampleCreatedDomainEvent event) {
        logger.info("Event: {}", event);
    }

}