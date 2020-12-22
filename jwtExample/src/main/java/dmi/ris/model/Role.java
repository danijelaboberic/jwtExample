package dmi.ris.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idRole;

	private String value;

	//bi-directional many-to-one association to UsersRole
	@OneToMany(mappedBy="role")
	private List<UsersRole> usersRoles;

	public Role() {
	}

	public int getIdRole() {
		return this.idRole;
	}

	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<UsersRole> getUsersRoles() {
		return this.usersRoles;
	}

	public void setUsersRoles(List<UsersRole> usersRoles) {
		this.usersRoles = usersRoles;
	}

	public UsersRole addUsersRole(UsersRole usersRole) {
		getUsersRoles().add(usersRole);
		usersRole.setRole(this);

		return usersRole;
	}

	public UsersRole removeUsersRole(UsersRole usersRole) {
		getUsersRoles().remove(usersRole);
		usersRole.setRole(null);

		return usersRole;
	}

}