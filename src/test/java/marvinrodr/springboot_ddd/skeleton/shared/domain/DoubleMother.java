package marvinrodr.springboot_ddd.skeleton.shared.domain;

public final class DoubleMother {
    public static Double random() {
        return MotherCreator.random().number().randomDouble(2, 0, 100);
    }
    public static Double random(Integer maxDecimals, Integer min, Integer max) {
        return MotherCreator.random().number().randomDouble(maxDecimals, min, max);
    }
}
