package marvinrodr.springboot_ddd.skeleton.shared.infrastructure.bus;

import marvinrodr.springboot_ddd.skeleton.App;
import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.DomainEvent;
import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.DomainEventSubscriber;
import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.bus.DomainEventSubscriberInformation.SubscribedEvent;

import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public final class DomainEventSubscribersInformation {

    private final List<DomainEventSubscriberInformation> information;

    public DomainEventSubscribersInformation(List<DomainEventSubscriberInformation> information) {
        this.information = information;
    }

    public DomainEventSubscribersInformation() {
        this(scanDomainEventSubscribers());
    }

    private static List<DomainEventSubscriberInformation> scanDomainEventSubscribers() {
        final Reflections reflections = new Reflections(ClasspathHelper.forPackage(App.PACKAGE_BASE));
        final Set<Class<?>> subscribers = reflections.getTypesAnnotatedWith(DomainEventSubscriber.class);

        return subscribers.stream().map(subscriberClass -> {
            final Class<? extends DomainEvent>[] domainEventSubscribers = subscriberClass.getAnnotation(
                    DomainEventSubscriber.class
            ).value();
            final List<SubscribedEvent> subscribedEvents = Arrays.stream(domainEventSubscribers)
                    .map(DomainEventSubscribersInformation::mapToSubscribedEvent)
                    .collect(Collectors.toList());

            return new DomainEventSubscriberInformation(subscriberClass, subscribedEvents);
        }).collect(Collectors.toList());
    }

    private static SubscribedEvent mapToSubscribedEvent(final Class<? extends DomainEvent> domainEvent) {
        try {
            final DomainEvent nullInstance = domainEvent.getConstructor().newInstance();
            final String eventName = nullInstance.eventName();

            return new SubscribedEvent(domainEvent, eventName);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public List<DomainEventSubscriberInformation> all() {
        return information;
    }

    public DomainEventSubscriberInformation get(final Class<?> expectedEventClass) {
        return information.stream()
                .filter(information -> expectedEventClass.equals(information.subscribedEvent().clazz()))
                .findFirst()
                .orElseThrow();
    }

}
