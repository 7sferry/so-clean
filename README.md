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
