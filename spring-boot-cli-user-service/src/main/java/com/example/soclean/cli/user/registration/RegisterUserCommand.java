package com.example.soclean.cli.user.registration;

import com.example.soclean.usecase.user.registration.RegisterUserRequest;
import com.example.soclean.usecase.user.registration.RegisterUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.CommandRegistration.OptionArity;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Command
@RequiredArgsConstructor
public class RegisterUserCommand {

	private final RegisterUserUseCase registerUserUseCase;

	@Command(command = "register-user", description = "Register a new user")
	public String register(@Option(arity = OptionArity.EXACTLY_ONE) String username,
						  @Option(arity = OptionArity.EXACTLY_ONE) String password) {
		RegisterUserCliPresenter presenter = new RegisterUserCliPresenter();
		registerUserUseCase.execute(new RegisterUserRequest(username, password), presenter);
		return presenter.getOutput();
	}

}
