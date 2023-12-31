package marvinrodr.springboot_ddd.skeleton.shared.domain.exception;

public class IllegalEnumValueException extends IllegalArgumentException {

    private static final String FORMAT_MESSAGE = "Unable to find any <%s> from this value <%s>";

    public IllegalEnumValueException(
            final Class<?> clazz,
            final String value
    ) {
        super(String.format(FORMAT_MESSAGE, clazz.getSimpleName(), value));
    }

}
