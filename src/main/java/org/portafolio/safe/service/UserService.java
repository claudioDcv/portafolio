package org.portafolio.safe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.portafolio.safe.dto.LoginDto;
import org.portafolio.safe.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
	
	@Transactional
	public Long insert(User user);
	
	public List<User> findAll(); 
	
	List<User> getAllUsers();
	
	User findById(Long id);
	
	User findByEmail(String email);
	
	LoginDto login(User user);

	@Transactional
	Long register(User user);
	
	@Transactional
	Long delete (Long id);
	
	Long updateSP(User user);
}
