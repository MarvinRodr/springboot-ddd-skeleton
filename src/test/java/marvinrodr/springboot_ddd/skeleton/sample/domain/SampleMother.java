package marvinrodr.springboot_ddd.skeleton.sample.domain;

import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.model.SampleId;

public final class SampleMother {

    public static Sample create(final SampleId id) {
        return new Sample(id);
    }

    public static Sample random() {
        return create(SampleIdMother.random());
    }

}
