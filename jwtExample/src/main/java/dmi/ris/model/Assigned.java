package dmi.ris.model;

import java.io.Serializable;

import jakarta.persistence.*;


/**
 * The persistent class for the assigned database table.
 * 
 */
@Entity
@Table(name="assigned")
@NamedQuery(name="Assigned.findAll", query="SELECT a FROM Assigned a")
public class Assigned implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idAssigned;

	//bi-directional many-to-one association to Task
	@ManyToOne
	@JoinColumn(name="idTask")
	private Task task;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="idUser")
	private User user;

	public Assigned() {
	}

	public int getIdAssigned() {
		return this.idAssigned;
	}

	public void setIdAssigned(int idAssigned) {
		this.idAssigned = idAssigned;
	}

	public Task getTask() {
		return this.task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}