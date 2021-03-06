package org.portafolio.safe.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(name = "users_get_all",
            procedureName = "users_get_all",
			resultClasses = User.class,
			parameters = {
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_by_id",
			procedureName = "users_by_id",
			resultClasses= { User.class},
			parameters = {
					@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "o_CURSOR", type = void.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_insert",
			procedureName = "users_insert",
			resultClasses= { User.class},
			parameters = {
					@StoredProcedureParameter(name="p_EMAIL", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_DISPLAY_NAME", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_PASSWORD", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="o_USER_ID", mode = ParameterMode.OUT, type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_delete",
			procedureName = "users_delete",
			resultClasses= { User.class},
			parameters = {
					@StoredProcedureParameter(name="p_id", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="o_delete_id", mode = ParameterMode.OUT, type = Long.class)
			}
	),
	@NamedStoredProcedureQuery(
			name = "users_update",
			procedureName = "users_update",
			resultClasses= { User.class},
			parameters = {
					@StoredProcedureParameter(name="p_EMAIL", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_DISPLAY_NAME", mode = ParameterMode.IN, type = String.class),
					@StoredProcedureParameter(name="p_USER_ID", mode = ParameterMode.IN, type = Long.class),
					@StoredProcedureParameter(name="o_USER_ID", mode = ParameterMode.OUT, type = Long.class)
			}
	)
})
@Entity
@Table(name="USERS")
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = -583948100923349227L;

	@Id
	@Column(name="USER_ID")
	@Getter @Setter Long id;
	
	@Column(name="DISPLAY_NAME")
	@Getter @Setter String displayName;

	@Email
	@Getter @Setter String email;
	
	@Column(name="password")
	@Getter @Setter String password;

	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
		name = "users_profiles",
		joinColumns = @JoinColumn(name="users_fk", referencedColumnName="user_id"),
		inverseJoinColumns = @JoinColumn(
			name = "profiles_fk",
			referencedColumnName = "profile_id"
		)
	)
	@Getter @Setter List<Profile> profiles;
}
