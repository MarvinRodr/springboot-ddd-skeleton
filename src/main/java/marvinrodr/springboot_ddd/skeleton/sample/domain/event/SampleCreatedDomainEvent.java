package marvinrodr.springboot_ddd.skeleton.sample.domain.event;

import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.model.SampleId;
import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.DomainEvent;

public final class SampleCreatedDomainEvent extends DomainEvent {

    private static final String EVENT_NAME = "sample.created";
    private final String name = "My awesome sample test";

    public SampleCreatedDomainEvent() {
        super();
    }

    @Override
    public String eventName() {
        return EVENT_NAME;
    }


    public SampleCreatedDomainEvent(final Sample value) {
        super(value.id());
    }

    public SampleCreatedDomainEvent(final SampleId aggregateId) {
        super(aggregateId);
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return "ArticleCreatedDomainEvent{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }

}
