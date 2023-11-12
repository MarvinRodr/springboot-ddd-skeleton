
# :microscope: SpringBoot DDD API Skeleton :microscope:

[![MarvinRodr](https://img.shields.io/badge/Marvin%20Rodriguez-github?style=flat-square)](https://github.com/MarvinRodr)
[![CI pipeline status](https://github.com/MarvinRodr/springboot-ddd-skeleton/actions/workflows/ci.yml/badge.svg)](https://github.com/MarvinRodr/springboot-ddd-skeleton/actions)

### This template serve as a starting point if you want to <strong>bootstrap a SpringBoot DDD API</strong>.

### ⚡ Steps to run the project

1. ```make build```
2. ```make run```
3. Run tests: ```make docker-test```
4. If necessary feel free to use the [Makefile](Makefile).
5. Feel free to try postman [collection](postman/SpringBoot_DDD_Skeleton-API.postman_collection.json)


🚃 Modules
---
```sh
src/main/java/marvinrodr/springboot_ddd/skeleton/
├── sample
│   ├── application
│   ├── domain
│   └── infrastructure
└── shared
    ├── application
    ├── domain
    └── infrastructure
```

🎬 Sample module
---
#### The "sample" module is just an example. It could be the module "user", "player", "whatever", etc.
```sh
src/Modules/Players/Application/
sample/application
├── create
│   ├── OnSampleCreatedEvent.java
│   └── SampleCreator.java
└── SampleRequest.java
```

🎒 Shared module
---
#### The "shared" module contains all the integrations and interactions that occur from outside our application. This is where the adapter pattern is implemented.
```sh
shared
├── application
│   └── response
├── domain
│   ├── bus
│   │   ├── event
│   │   └── query
│   ├── email
│   ├── exception
│   ├── log
│   ├── repository
│   ├── transaction_handler
│   ├── util
│   └── value_object
│       └── id
└── infrastructure
    ├── bus
    ├── config
    ├── controller
    ├── dependency_injection
    ├── hibernate
    ├── logger
    ├── swagger
    ├── transaction_handler
    ├── util
    └── validation
        └── validators
```
