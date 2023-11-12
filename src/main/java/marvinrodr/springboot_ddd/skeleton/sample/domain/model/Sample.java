package marvinrodr.springboot_ddd.skeleton.sample.domain.model;

import marvinrodr.springboot_ddd.skeleton.sample.domain.event.SampleCreatedDomainEvent;
import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.AggregateRoot;

import java.util.Objects;

public final class Sample extends AggregateRoot {
    private final SampleId id;

    private Sample() {
        id = null;
    }

    public Sample(SampleId id) {
        this.id = id;
    }

    public static Sample create(final SampleId id) {
        final Sample sample = new Sample(id);
        sample.record(new SampleCreatedDomainEvent(sample));

        return sample;
    }

    public SampleId id() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sample course = (Sample) o;

        assert id != null;
        return id.equals(course.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
