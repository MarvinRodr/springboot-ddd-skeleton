package marvinrodr.springboot_ddd.skeleton.shared.infrastructure;

import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.DomainEvent;
import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.EventBus;
import marvinrodr.springboot_ddd.skeleton.shared.domain.repository.GenericRepository;
import marvinrodr.springboot_ddd.skeleton.shared.domain.util.UuidGenerator;
import marvinrodr.springboot_ddd.skeleton.shared.domain.value_object.id.Identifier;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

public abstract class UnitTestCase<T, R extends GenericRepository<T>> {
    protected final R repository;
    protected UuidGenerator uuidGenerator;
    protected final EventBus eventBus;


    protected UnitTestCase(final Class<R> repositoryClass) {
        this.repository = mock(repositoryClass);
        this.eventBus = mock(EventBus.class);
    }

    // TODO: Implement event published tests methods

    public void shouldGenerateUuid(String uuid) {
        when(this.uuidGenerator.generate()).thenReturn(uuid);
    }

    public void shouldHaveCreated(T model) {
        verify(repository, atLeastOnce()).createOrUpdate(refEq(model, "id"));
    }

    public void shouldHaveUpdated(T model) {
        verify(repository, atLeastOnce()).createOrUpdate(model);
    }

    protected void shouldHaveErased(final Identifier id) {
        verify(repository, atLeastOnce()).deleteById(id);
    }

    protected void shouldHaveFoundById(final Identifier id) {
        verify(repository, atLeastOnce()).getById(id);
    }

    public void shouldGenerateUuids(String uuid, String... others) {
        when(this.uuidGenerator.generate()).thenReturn(uuid, others);
    }

    protected void shouldHavePublishedDomainEvents(final List<DomainEvent> domainEvents) {
        verify(eventBus, atLeastOnce()).publish(argThat(args -> domainEvents.size() == args.size()));
    }

    public void shouldHavePublished(DomainEvent domainEvent) {
        shouldHavePublishedDomainEvents(Collections.singletonList(domainEvent));
    }
}
