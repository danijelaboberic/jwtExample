package dmi.ris.model;

import java.io.Serializable;
import jakarta.persistence.*;


/**
 * The persistent class for the picture database table.
 * 
 */
@Entity
@Table(name="picture")
@NamedQuery(name="Picture.findAll", query="SELECT p FROM Picture p")
public class Picture implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idPicture;

	private String path;

	@Lob
	private byte[] picture;

	//bi-directional many-to-one association to Project
	@ManyToOne
	@JoinColumn(name="idProject")
	private Project project;

	public Picture() {
	}

	public int getIdPicture() {
		return this.idPicture;
	}

	public void setIdPicture(int idPicture) {
		this.idPicture = idPicture;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

}