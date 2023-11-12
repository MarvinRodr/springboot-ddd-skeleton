package marvinrodr.springboot_ddd.skeleton.sample.application;

public record SampleRequest (String id) {
    public static SampleRequest of(final String id) {
        return new SampleRequest(id);
    }
}
