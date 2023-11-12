package marvinrodr.springboot_ddd.skeleton.shared.infrastructure.util;

import marvinrodr.springboot_ddd.skeleton.shared.domain.Service;
import marvinrodr.springboot_ddd.skeleton.shared.domain.util.UuidGenerator;

import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
