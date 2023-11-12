package marvinrodr.springboot_ddd.skeleton.shared.infrastructure.hibernate;

import marvinrodr.springboot_ddd.skeleton.shared.domain.value_object.id.Identifier;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public abstract class HibernateRepository<T> {

    protected final EntityManager entityManager;
    protected final Class<T> aggregateClass;
    protected final CriteriaBuilder criteriaBuilder;

    public HibernateRepository(final EntityManager entityManager, final Class<T> aggregateClass) {
        this.entityManager = entityManager;
        this.aggregateClass = aggregateClass;
        this.criteriaBuilder = this.entityManager.getCriteriaBuilder();
    }

    protected void save(final T entity) {
        this.persist(entity);
        this.flush();
        this.clear();
    }

    protected void update(final T entity) {
        this.entityManager.merge(entity);
        this.flush();
        this.clear();
    }

    public Optional<T> getById(final Identifier id) {
        return Optional.ofNullable(this.entityManager.find(aggregateClass, id));
    }

    public void delete(final T entity) {
        this.entityManager.remove(entity);
        this.flush();
        this.clear();
    }

    public void deleteById(final Identifier id) {
        Optional<T> entity = this.getById(id);

        entity.ifPresent(this::delete);
    }

    protected void persist(final T entity) {
        this.entityManager.persist(entity);
    }

    protected void flush() {
        this.entityManager.flush();
    }

    protected void clear() {
        this.entityManager.clear();
    }
}
