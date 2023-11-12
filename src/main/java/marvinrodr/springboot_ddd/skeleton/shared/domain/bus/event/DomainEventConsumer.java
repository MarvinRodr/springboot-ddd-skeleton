package marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface DomainEventConsumer {
    Class<? extends DomainEvent>[] value();

}
