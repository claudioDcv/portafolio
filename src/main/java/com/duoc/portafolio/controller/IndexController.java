package com.duoc.portafolio.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.portafolio.entity.Riesgo;
import com.duoc.portafolio.service.RiesgoServiceImpl;


@RestController
public class IndexController {

	@Autowired
	RiesgoServiceImpl riesgoServiceImpl;
	
	@RequestMapping("/index")
	public String index() {		
		return "API v1";
	}
	
	@RequestMapping("/riesgo")
	public List<Riesgo> findAllRiesgo() {		
		return riesgoServiceImpl.gettAllWithProcedure();
	}
	
	@RequestMapping(value = "/riesgo/{id}", method = RequestMethod.GET)
	public Riesgo getFoosBySimplePathWithPathVariable(@PathVariable("id") Long id) {
		try {
			Riesgo r = riesgoServiceImpl.get(id);
			return r;
		} catch (Exception e) {
			return new Riesgo();
		}
		
	}
}
