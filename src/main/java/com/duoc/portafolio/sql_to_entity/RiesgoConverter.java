package com.duoc.portafolio.sql_to_entity;

import java.util.ArrayList;
import java.util.List;

import com.duoc.portafolio.entity.Riesgo;

public final class RiesgoConverter {
	
	public static final List<Riesgo> list(List<Object[]> l) {
		List<Riesgo> lr = new ArrayList<Riesgo>();
		for (Object[] o : l) {
			lr.add(new Riesgo(Long.parseLong(o[0] + ""), (String)o[1]));
		}
		return lr;
	}
}
