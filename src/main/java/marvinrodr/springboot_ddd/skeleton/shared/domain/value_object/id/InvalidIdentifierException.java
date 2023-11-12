package marvinrodr.springboot_ddd.skeleton.shared.domain.value_object.id;

import marvinrodr.springboot_ddd.skeleton.shared.domain.exception.DomainException;

public final class InvalidIdentifierException extends DomainException {

    private static final String ERROR_CODE = "invalid_id";

    private InvalidIdentifierException(String value) {
        super(ERROR_CODE, String.format("The id <%s> is invalid", value));
    }

    public static void throwCauseOf(String value) {
        throw new InvalidIdentifierException(value);
    }

}
