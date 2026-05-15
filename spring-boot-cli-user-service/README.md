# spring-boot-cli-user-service

A CLI application for the user service built with Clean Architecture.

## Modules

```
spring-boot-cli-user-service/
└── src/main/java/com/example/soclean/cli/
    ├── CliApplication.java                         Spring Boot CLI entry point.
    └── user/
        ├── UserConfig.java                         CLI user configuration.
        ├── detail/
        │   ├── GetUserDetailCliPresenter.java       CLI presenter for user detail.
        │   └── GetUserDetailCommand.java            CLI command to get user detail.
        └── registration/
            ├── RegisterUserCliPresenter.java        CLI presenter for user registration.
            └── RegisterUserCommand.java             CLI command to register a user.
```