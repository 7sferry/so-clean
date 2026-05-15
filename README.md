# so-clean

A user service built with Clean Architecture.

## Modules

```
so-clean/
├── user-domain/                        Pure Java. DTOs, records, domain models. No framework dependencies.
├── user-usecase/                       Business logic. Use case interfaces/impls, gateway and presenter interfaces.
├── spring-data-jpa-user-repository/    Data access with Spring Data JPA. Swap with jooq-user-repository.
└── spring-boot-user-service/           Spring Boot app. Controllers, presenter implementations, config.
```
