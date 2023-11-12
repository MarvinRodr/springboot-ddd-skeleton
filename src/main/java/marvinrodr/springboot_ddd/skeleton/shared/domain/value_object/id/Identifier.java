package marvinrodr.springboot_ddd.skeleton.shared.domain.value_object.id;

import marvinrodr.springboot_ddd.skeleton.shared.domain.value_object.StringValueObject;

import java.io.Serializable;
import java.util.UUID;

public class Identifier extends StringValueObject implements Serializable {

    protected Identifier() {
        this(randomValue());
    }

    protected Identifier(final String value) {
        super(value);
        ensureIsValid(value);
    }

    @SuppressWarnings("all")
    private void ensureIsValid(String value) {
        try {
            UUID.fromString(value);
        } catch (Exception e) {
            InvalidIdentifierException.throwCauseOf(value);
        }
    }

    public static Identifier random() {
        return new Identifier(randomValue());
    }

    private static String randomValue() {
        return UUID.randomUUID().toString();
    }

}
