package com.duoc.portafolio.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.procedure.ProcedureCall;

import org.hibernate.result.Output;
import org.hibernate.result.ResultSetOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.portafolio.entity.Riesgo;
import com.duoc.portafolio.repository.RiesgoRepository;
import com.duoc.portafolio.sql_to_entity.RiesgoConverter;


@Service("riesgoService")
@Transactional
public class RiesgoServiceImpl implements RiesgoService {
	
	@PersistenceContext
    private EntityManager em;

	@Autowired
	private RiesgoRepository riesgoRepository;
	
	@Override
	public List<Riesgo> findAll() {
		return null;
	}

	@Override
	public Riesgo get_riesgo() {
		return null;
	}
	
	public List<Riesgo> gettAllWithProcedure() {	
		StoredProcedureQuery query = em
				.createStoredProcedureQuery("get_All_riesgo")
			    .registerStoredProcedureParameter(1, Riesgo.class, ParameterMode.REF_CURSOR);
			query.execute();
		return RiesgoConverter.list(query.getResultList());
		// query
		// return em.createNativeQuery("select riesgo_ID, nombre from riesgo", Riesgo.class).getResultList();
	}
	
	public Riesgo get(Long id) {	
		Query q = em.createNativeQuery("SELECT a.riesgo_id, a.nombre FROM riesgo a WHERE a.riesgo_id = ?", Riesgo.class);
		q.setParameter(1, id);

		return (Riesgo) q.getSingleResult();
		// return em.createNativeQuery("select riesgo_ID, nombre from riesgo", Riesgo.class).getResultList();
	}

}
