package marvinrodr.springboot_ddd.skeleton.sample;

import marvinrodr.springboot_ddd.skeleton.sample.domain.model.Sample;
import marvinrodr.springboot_ddd.skeleton.sample.domain.repository.SampleRepository;
import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.UnitTestCase;

public abstract class SamplesModuleUnitTestCase extends UnitTestCase<Sample, SampleRepository> {

    protected SamplesModuleUnitTestCase() {
        super(SampleRepository.class);
    }
}
