package com.example.soclean.cli.user.detail;

import com.example.soclean.domain.user.detail.GetUserDetailRequest;
import com.example.soclean.usecase.user.detail.GetUserDetailUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.CommandRegistration.OptionArity;
import org.springframework.shell.command.annotation.Option;

/************************
 * Author: [MR FERRY™]  *
 * May 2026             *
 ************************/

@Command
@RequiredArgsConstructor
public class GetUserDetailCommand {

	private final GetUserDetailUseCase getUserDetailUseCase;

	@Command(command = "get-detail-user", description = "Get user detail by username")
	public String getDetail(@Option(arity = OptionArity.EXACTLY_ONE) String username) {
		GetUserDetailCliPresenter presenter = new GetUserDetailCliPresenter();
		getUserDetailUseCase.execute(new GetUserDetailRequest(username), presenter);
		return presenter.getOutput();
	}

}
