package marvinrodr.springboot_ddd.skeleton.sample.domain;

import marvinrodr.springboot_ddd.skeleton.sample.domain.event.SampleCreatedDomainEvent;
import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.model.SampleId;

public final class SampleCreatedDomainEventMother {
    public static SampleCreatedDomainEvent create(SampleId id) {
        return new SampleCreatedDomainEvent(id);
    }

    public static SampleCreatedDomainEvent fromSample(Sample sample) {
        return create(sample.id());
    }

    public static SampleCreatedDomainEvent random() {
        return create(SampleIdMother.random());
    }
}
