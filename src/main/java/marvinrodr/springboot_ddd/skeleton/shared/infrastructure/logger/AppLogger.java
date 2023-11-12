package marvinrodr.springboot_ddd.skeleton.shared.infrastructure.logger;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import marvinrodr.springboot_ddd.skeleton.shared.domain.log.Logger;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.core.MethodParameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Optional;

public final class AppLogger implements Logger {

    private final org.apache.logging.log4j.Logger logger;
    private final ObjectWriter objectWriter = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .setDateFormat(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"))
            .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
            .writerWithDefaultPrettyPrinter();

    public AppLogger(final InjectionPoint ip) {
        this.logger = LogManager.getLogger(getClassName(ip));
    }

    private String getClassName(final InjectionPoint ip) {
        return Optional.ofNullable(getMethodOrElseNull(ip.getMethodParameter()))
                .map(m -> m.getReturnType().getName())
                .orElseGet(() -> getMethodParameterOfDeclaredClass(ip));
    }

    private Method getMethodOrElseNull(final MethodParameter methodParameter) {
        try {
            return methodParameter.getMethod();
        } catch (Exception e) {
            return null;
        }
    }

    private String getMethodParameterOfDeclaredClass(final InjectionPoint ip) {
        return Optional.ofNullable(ip.getMethodParameter())
                .map(mp -> mp.getDeclaringClass().getName())
                .orElseGet(() -> getFieldParameterOfDeclaredClass(ip));
    }

    private String getFieldParameterOfDeclaredClass(final InjectionPoint ip) {
        return Optional.ofNullable(ip.getField())
                .map(f -> f.getDeclaringClass().getName())
                .orElseThrow(IllegalArgumentException::new);
    }

    private String convertToJson(Object object) {
        try {
            return objectWriter.writeValueAsString(object);
        } catch (Throwable e) {
            return object.toString();
        }
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void warn(String message) {
        logger.warn(message);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void debug(String message) {
        logger.debug(message);
    }

    @Override
    public void info(String format, Object object) {
        logger.info(format, convertToJson(object));
    }

    @Override
    public void warn(String format, Object object) {
        logger.warn(format, convertToJson(object));
    }

    @Override
    public void error(String format, Object object) {
        logger.error(format, convertToJson(object));
    }

    @Override
    public void debug(String format, Object object) {
        logger.debug(format, convertToJson(object));
    }

}
