package marvinrodr.springboot_ddd.skeleton.shared.domain.repository;

import marvinrodr.springboot_ddd.skeleton.shared.domain.value_object.id.Identifier;

import java.util.Optional;

public interface GenericRepository<T> {

    Optional<T> getById(final Identifier id);

    void createOrUpdate(final T model);

    void deleteById(final Identifier id);

}
