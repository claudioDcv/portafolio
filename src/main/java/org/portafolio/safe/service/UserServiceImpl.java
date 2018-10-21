package org.portafolio.safe.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.portafolio.safe.dto.LoginDto;
import org.portafolio.safe.entity.Profile;
import org.portafolio.safe.entity.User;
import org.portafolio.safe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class UserServiceImpl implements UserService {
	
	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public Long insert(User user) {
		
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_insert");
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
		query.setParameter("p_EMAIL", user.getEmail());
		query.setParameter("p_DISPLAY_NAME", user.getDisplayName());
		query.setParameter("p_PASSWORD", bcryptHashString);
		query.execute();
		return (Long) query.getOutputParameterValue("o_USER_ID");
	}

	@Override
	public List<User> findAll() {
		return null;
	}

	public User findById(Long id) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_by_id");
		query.setParameter("p_id", id);
		return (User) query.getSingleResult();
	}
	
	@Override
	public List<User> getAllUsers() {
        StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_get_all");
        return query.getResultList();
    }

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).get(0);
	}

	@Override
	public LoginDto login(User user) {
		String password = user.getPassword();
		LoginDto loginDto = new LoginDto();
		try {
			User userFromDB = this.findByEmail(user.getEmail());
			BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), userFromDB.getPassword());	
			
			Boolean hasProfile = Boolean.FALSE;
			for (Profile profile : userFromDB.getProfiles()) {
				for (Profile profileExternal : user.getProfiles()) {
					if (profile.getId() == profileExternal.getId()) {
						hasProfile = Boolean.TRUE;
					}
				}				
			}
			
			if (result.verified) {
				loginDto.setUser(userFromDB);
			}
			loginDto.setMessage(hasProfile ? "" : "No Tiene Permisos suficientes");
			loginDto.setHasLogin(result.verified && hasProfile);
			return loginDto;
		} catch (IndexOutOfBoundsException e) {
			return loginDto;
		}
	}

	@Override
	public Long register(User user) {
		return this.insert(user);
	}

	@Override
	public Long delete(Long id) {
		Long DEFAULT = (long) 0;
		try {
			StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_delete");
			query.setParameter("p_id", id);
			query.execute();
			return (Long) query.getOutputParameterValue("o_delete_id");
		} catch (Exception e) {
			return DEFAULT;
		}
	}

	@Override
	public Long updateSP(User user) {
		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("users_update");
		query.setParameter("p_EMAIL", user.getEmail());
		query.setParameter("p_DISPLAY_NAME", user.getDisplayName());
		query.setParameter("p_USER_ID", user.getId());
		query.execute();
		return (Long) query.getOutputParameterValue("o_USER_ID");
	}
	
}
