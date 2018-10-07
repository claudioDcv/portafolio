package com.duoc.portafolio.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.duoc.portafolio.entity.Riesgo;

@Transactional
public interface RiesgoService {
	public List<Riesgo> findAll();
	
	public Riesgo get_riesgo();
}
