package com.duoc.portafolio.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import lombok.Data;

/*
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name="getAll",
			resultClasses = Riesgo.class,
			procedureName="get_all_riesgo",
			parameters={
					@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, type = void.class)})
	})
	
	@NamedStoredProcedureQueries({
@NamedStoredProcedureQuery(
		name="getAll",
		resultClasses = Riesgo.class,
		procedureName="get_all_riesgo",
		parameters={
				@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR, type = void.class)
		}
),
@NamedStoredProcedureQuery(
		name="getProcedure",
		resultClasses = Riesgo.class,
		procedureName="get_riesgo",
		parameters={
				@StoredProcedureParameter(name="P_RIESGO_ID", mode=ParameterMode.OUT, type = Integer.class),
				@StoredProcedureParameter(name="P_NOMBRE", mode=ParameterMode.OUT, type = String.class)
		}
)
})
	*/
/*
@NamedStoredProcedureQueries({ 
@NamedStoredProcedureQuery(
		name="Riesgo.getAll",
		resultClasses = Riesgo.class,
		procedureName="get_all_riesgo",
		parameters={
				@StoredProcedureParameter(
						name="riesgo_o",
						mode=ParameterMode.REF_CURSOR,
						type=Void.class
				)
		}
),
@NamedStoredProcedureQuery(
		name="Riesgo.getProcedure",
		resultClasses = Riesgo.class,
		procedureName="get_riesgo",
		parameters={
				@StoredProcedureParameter(name="P_RIESGO_ID", mode=ParameterMode.OUT, type = Integer.class),
				@StoredProcedureParameter(name="P_NOMBRE", mode=ParameterMode.OUT, type = String.class)
		}
)
})
*/
@Data
@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "Riesgo.get_riesgo",
        procedureName = "get_riesgo",
        parameters = {
          @StoredProcedureParameter(mode=ParameterMode.OUT, name="p_riesgo_id", type=Integer.class),
          @StoredProcedureParameter(mode=ParameterMode.OUT, name="p_nombre", type=String.class)
    })
})
@Table(name="riesgo")
public class Riesgo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
		
	public Riesgo() {
		super();
	}

	public Riesgo(Long id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="riesgo_ID")
	private Long id;
	
	@Column
	private String nombre;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	
}