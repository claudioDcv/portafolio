package org.portafolio.safe.controller;

import java.util.List;

import org.portafolio.safe.common.EmailValidator;
import org.portafolio.safe.dto.ResponseDto;
import org.portafolio.safe.entity.User;
import org.portafolio.safe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/usuario")
public class UserController {

	@Autowired
	UserService userService;
	
	@CrossOrigin
	@GetMapping("")
	public ResponseDto<List<User>> findAll() {
		ResponseDto<List<User>> responseDto = new ResponseDto<>();
		responseDto.setObject(userService.getAllUsers());
		return responseDto;
	}
	
	
	@CrossOrigin
	@PostMapping(value="/{id}")
	public User update(@RequestBody User user, @PathVariable(name="id") Long id) {

		return user;
	}
	
	@CrossOrigin
	@GetMapping(value="/{id}")
	public ResponseDto<User> findById(@PathVariable(name="id") Long id) {
		ResponseDto<User> responseDto = new ResponseDto<>();
		responseDto.setObject(userService.findById(id));
		return responseDto;
	}
	
	@CrossOrigin
	@PutMapping(value="/{id}")
	public ResponseDto<Long> updateWithoutPassword(@RequestBody User user, @PathVariable(name="id") Long id) {
		
		EmailValidator emailValidator = new EmailValidator();
		
		User userUpdate = new User();
		userUpdate.setId(id);
		userUpdate.setEmail(user.getEmail());
		userUpdate.setDisplayName(user.getDisplayName());
		ResponseDto<Long> responseDto = new ResponseDto<>();
		if (emailValidator.validateEmail(user.getEmail())) {
			responseDto.setObject(userService.updateSP(userUpdate));
		} else {
			responseDto.setObject((long) 0);
			responseDto.setMessage("email no es valido");
		}
		
		return responseDto;
	}
	
	@CrossOrigin
	@PostMapping(value="")
	public ResponseDto<User> insert(@RequestBody User user) {
		user.setId(userService.register(user));
		ResponseDto<User> responseDto = new ResponseDto<>();
		responseDto.setObject(user);
		return responseDto;
	}
	
	@CrossOrigin
	@DeleteMapping(value="/{id}")
	public ResponseDto<Long> deleteById(@PathVariable(name="id") Long id) {
		ResponseDto<Long> responseDto = new ResponseDto<>();
		responseDto.setObject(userService.delete(id));
		return responseDto;
	}
}
