package marvinrodr.springboot_ddd.skeleton.shared.domain;

public final class BoolMother {
    public static Boolean random() {
        return MotherCreator.random().bool().bool();
    }
}
