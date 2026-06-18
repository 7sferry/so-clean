# so-clean

A user service built with Clean Architecture.

## Modules

```
so-clean/
├── user-domain/                        Pure Java. DTOs, records, domain models. No framework dependencies.
├── user-usecase/                       Business logic. Use case interfaces/impls, gateway and presenter interfaces.
├── spring-data-jpa-user-repository/    Data access with Spring Data JPA.
├── jooq-user-repository/               Data access with Jooq.
└── spring-boot-user-service/           Spring Boot app. Controllers, presenter implementations, config.
└── spring-boot-cli-user-service/       Spring Boot shell app version.
```

for jooq:
```sh
mvn clean install -pl jooq-user-repository //only at the first time

mvn generate-sources -pl jooq-user-repository
```
The Layers (inside → out)

1. Entities — Enterprise-wide business rules. Pure domain objects (e.g., Order, User). No framework, no DB, no I/O.
2. Use Cases — Application-specific business rules. Orchestrate entities to fulfill a user action (e.g., PlaceOrder,
   TransferFunds).
3. Interface Adapters — Controllers, presenters, gateways. Translate between use cases and the outside world (HTTP
   requests → use case input, use case output → JSON).
4. Frameworks & Drivers — Web framework, DB, UI, external APIs. The replaceable details.
