package marvinrodr.springboot_ddd.skeleton.sample.domain.model;

import marvinrodr.springboot_ddd.skeleton.shared.domain.value_object.id.Identifier;

public final class SampleId extends Identifier {
    public SampleId() {}

    public SampleId(String value) {
        super(value);
    }

    public static SampleId random() {
        return new SampleId();
    }
}
