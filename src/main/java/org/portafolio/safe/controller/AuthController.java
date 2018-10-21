package org.portafolio.safe.controller;

import org.portafolio.safe.dto.LoginDto;
import org.portafolio.safe.dto.ResponseDto;
import org.portafolio.safe.entity.User;
import org.portafolio.safe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping(value="/auth")
public class AuthController {
	
	@Autowired
	UserService userService;
	
	@CrossOrigin
	@PostMapping(value="/login")
	public ResponseDto<LoginDto> login(@RequestBody User user) {
		
		ResponseDto<LoginDto> responseDto = new ResponseDto<>();
		LoginDto loginDto = userService.login(user);
		loginDto.getUser().setPassword(null);
		responseDto.setObject(loginDto);
		responseDto.setMessage(loginDto.getMessage());
		return responseDto;
	}
	
	@CrossOrigin
	@PostMapping(value="/register")
	public ResponseDto<User> insert(@RequestBody User user) {
		ResponseDto<User> responseDto = new ResponseDto<>();
		
		user.setId(userService.insert(user));
		
		responseDto.setObject(user);
		return responseDto;
	} 
}
