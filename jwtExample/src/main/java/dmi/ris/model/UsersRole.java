package dmi.ris.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the UsersRole database table.
 * 
 */
@Entity
@NamedQuery(name="UsersRole.findAll", query="SELECT u FROM UsersRole u")
public class UsersRole implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="idRoleFK")
	private Role role;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUserFK")
	private User user;

	public UsersRole() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}