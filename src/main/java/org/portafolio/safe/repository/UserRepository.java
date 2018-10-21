package org.portafolio.safe.repository;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

import org.portafolio.safe.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByEmail(String email);

}
