package org.portafolio.safe.controller;

import java.util.List;

import org.portafolio.safe.dto.ResponseDto;
import org.portafolio.safe.entity.Profile;
import org.portafolio.safe.entity.User;
import org.portafolio.safe.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="perfil")
public class ProfileSAFEController {

	@Autowired
	private ProfileService profileService;
	
	@CrossOrigin
	@GetMapping(value="/por-id-usuario/{id}")
	public ResponseDto<List<Profile>> getProfileByUserId(@PathVariable(name="id") Long id) {
		ResponseDto<List<Profile>> responseDto = new ResponseDto<>();
		responseDto.setObject(profileService.getProfileByUserId(id));
		return responseDto;
	}
	
	@CrossOrigin
	@GetMapping("")
	public ResponseDto<List<Profile>> findAll() {
		ResponseDto<List<Profile>> responseDto = new ResponseDto<>();
		responseDto.setObject(profileService.getAllProfiles());
		return responseDto;
	}
}
