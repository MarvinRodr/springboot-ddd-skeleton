package marvinrodr.springboot_ddd.skeleton.shared.domain.log;

public interface Logger {

    void info(String $message);

    void warn(String $message);

    void error(String $message);

    void debug(String $message);

    void info(final String format, final Object object);

    void warn(final String format, final Object object);

    void error(final String format, final Object object);

    void debug(final String format, final Object object);

    default void infoF(final String format, final Object... arguments) {
        this.info(String.format(format, arguments));
    }

    default void warnF(final String format, final Object... arguments) {
        this.warn(String.format(format, arguments));
    }

    default void errorF(final String format, final Object... arguments) {
        this.error(String.format(format, arguments));
    }

    default void debugF(final String format, final Object... arguments) {
        this.debug(String.format(format, arguments));
    }

}
