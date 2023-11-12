package marvinrodr.springboot_ddd.skeleton.shared.infrastructure.dependency_injection;

import marvinrodr.springboot_ddd.skeleton.shared.domain.bus.event.EventBus;
import marvinrodr.springboot_ddd.skeleton.shared.domain.log.Logger;
import marvinrodr.springboot_ddd.skeleton.shared.domain.transaction_handler.TransactionHandler;
import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.bus.DomainEventSubscribersInformation;
import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.bus.ReactorEventBus;
import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.logger.AppLogger;
import marvinrodr.springboot_ddd.skeleton.shared.infrastructure.transaction_handler.SpringApplicationTransactionHandler;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class SharedModuleDependencyContainer {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Logger logger(final InjectionPoint ip) {
        return new AppLogger(ip);
    }

    @Bean
    public DomainEventSubscribersInformation domainEventSubscribersInformation() {
        return new DomainEventSubscribersInformation();
    }

    @Bean
    public EventBus reactorDomainEventBus(
            final ApplicationContext applicationContext,
            final DomainEventSubscribersInformation domainEventSubscribersInformation
    ) {
        return new ReactorEventBus(applicationContext, domainEventSubscribersInformation);
    }

    @Bean
    public TransactionHandler springApplicationTransactionHandler() {
        return new SpringApplicationTransactionHandler();
    }
}

