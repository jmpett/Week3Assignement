package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="players")
public class footballPlayer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="OWNER")
	private String owner; 
	@Column(name="NAME")
	private String name;
	@Column(name="TEAM")
	private String team;

	public footballPlayer() {

	}

	public footballPlayer(String owner, String name, String team) {
		this.owner = owner;
		this.name = name;
		this.team = team;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

 	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "ListTeam [id=" + id + ", name=" + name + ", team=" + team + "]";
	}

	public String returnTeamDetails() {
		return "[Athlete/Team/Owner] " + name + ": " + team + ": " + owner;
	}
}