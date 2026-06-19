package com.example.soclean.cli.user.detail;

import com.example.soclean.usecase.user.detail.UserDetailRequest;
import com.example.soclean.usecase.user.detail.UserDetailUseCase;
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
public class UserDetailCommand{

	private final UserDetailUseCase userDetailUseCase;

	@Command(command = "get-detail-user", description = "Get user detail by username")
	public String getDetail(@Option(arity = OptionArity.EXACTLY_ONE) String username) {
		UserDetailCliPresenter presenter = new UserDetailCliPresenter();
		userDetailUseCase.execute(new UserDetailRequest(username), presenter);
		return presenter.getOutput();
	}

}
