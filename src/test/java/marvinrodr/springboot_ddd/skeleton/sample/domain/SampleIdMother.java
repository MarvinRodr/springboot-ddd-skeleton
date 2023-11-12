package marvinrodr.springboot_ddd.skeleton.sample.domain;

import marvinrodr.springboot_ddd.skeleton.sample.domain.model.SampleId;
import marvinrodr.springboot_ddd.skeleton.shared.domain.UuidMother;

public final class SampleIdMother {
    public static SampleId create(String value) {
        return new SampleId(value);
    }

    public static SampleId random() {
        return create(UuidMother.random());
    }
}
