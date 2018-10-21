package org.portafolio.safe.common;

/**
 * 
 * @author rojas
 * @description representa los perfiles posibles, esto es el reflejo de la tabla perfiles en la base de datos
 */
public enum Profiles {
	TECNICO("tecnico"),
	SUPERADMIN("superadmin"),
	MEDICO("medico"),
	PREVENCIONISTA("prevencionista"),
	TRABAJADOR("trabajador"),
	EXAMINADOR("examinador");
	
	private String nameProfile;
	
	private Profiles (String nameProfile){
		this.nameProfile = nameProfile;
	}

	public String getNameProfile() {
		return nameProfile;
	}
}
