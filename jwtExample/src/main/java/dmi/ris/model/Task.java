package dmi.ris.model;

import java.io.Serializable;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the task database table.
 * 
 */
@Entity
@Table(name="task")
@NamedQuery(name="Task.findAll", query="SELECT t FROM Task t")
public class Task implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idTask;

	@Lob
	private String description;

	private Timestamp endDate;

	private Timestamp startDate;

	private String status;

	//bi-directional many-to-one association to Assigned
	@OneToMany(mappedBy="task")
	private Set<Assigned> assigneds;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="idProject")
	private Project project;

	public Task() {
	}

	public int getIdTask() {
		return this.idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Timestamp getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Assigned> getAssigneds() {
		return this.assigneds;
	}

	public void setAssigneds(Set<Assigned> assigneds) {
		this.assigneds = assigneds;
	}

	public Assigned addAssigned(Assigned assigned) {
		getAssigneds().add(assigned);
		assigned.setTask(this);

		return assigned;
	}

	public Assigned removeAssigned(Assigned assigned) {
		getAssigneds().remove(assigned);
		assigned.setTask(null);

		return assigned;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}