package com.duoc.portafolio.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.duoc.portafolio.entity.Riesgo;

@Repository("riesgoRepository")
public interface RiesgoRepository extends CrudRepository<Riesgo, Serializable> {
	
	@Query(value="SELECT * FROM RIESGO", nativeQuery=true)
	public List<Riesgo> findAll();

	@Procedure(name="get_riesgo")
	Riesgo get_riesgo();
}
