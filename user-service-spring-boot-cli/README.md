# spring-boot-cli-user-service

A CLI application for the user service built with Clean Architecture.

## Modules

```
user-service-spring-boot-cli/
└── src/main/java/com/example/soclean/cli/
    ├── UserCliApplication.java                         Spring Boot CLI entry point.
    └── user/
        ├── UserCliConfig.java                         CLI user configuration.
        ├── detail/
        │   ├── UserDetailCliPresenter.java       CLI presenter for user detail.
        │   └── UserDetailCommand.java            CLI command to get user detail.
        └── registration/
            ├── UserRegistrationCliPresenter.java        CLI presenter for user registration.
            └── UserRegistrationCommand.java             CLI command to register a user.
```
