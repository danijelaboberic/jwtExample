package dmi.ris.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Set;

/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idUser;

	private String name;

	private String password;

	private String username;

	//bi-directional many-to-one association to Assigned
	@OneToMany(mappedBy="user")
	private Set<Assigned> assigneds;

	//bi-directional many-to-one association to Project
	@OneToMany(mappedBy="user")
	private Set<Project> projects;

	//bi-directional many-to-one association to Role
	@ManyToOne
	@JoinColumn(name="idRole")
	private Role role;

	public User() {
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Assigned> getAssigneds() {
		return this.assigneds;
	}

	public void setAssigneds(Set<Assigned> assigneds) {
		this.assigneds = assigneds;
	}

	public Assigned addAssigned(Assigned assigned) {
		getAssigneds().add(assigned);
		assigned.setUser(this);

		return assigned;
	}

	public Assigned removeAssigned(Assigned assigned) {
		getAssigneds().remove(assigned);
		assigned.setUser(null);

		return assigned;
	}

	public Set<Project> getProjects() {
		return this.projects;
	}

	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}

	public Project addProject(Project project) {
		getProjects().add(project);
		project.setUser(this);

		return project;
	}

	public Project removeProject(Project project) {
		getProjects().remove(project);
		project.setUser(null);

		return project;
	}

	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}