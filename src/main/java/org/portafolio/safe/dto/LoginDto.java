package org.portafolio.safe.dto;

import org.portafolio.safe.entity.User;

import lombok.Getter;
import lombok.Setter;

public class LoginDto {
	@Getter @Setter User user;
	@Getter @Setter Boolean hasLogin;
	@Getter @Setter String message;
}
