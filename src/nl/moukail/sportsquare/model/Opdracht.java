package nl.moukail.sportsquare.model;

import java.io.Serializable;

public class Opdracht implements Serializable{
	
	private int id;
	private String name;
	private int serie;
	private int herhaal;
	private int trainingid;
		
	public int getOpdrachtId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	public void setOpdrachtId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}
	
	public String getOpdrachtName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setOpdrachtName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	
	public int getOpdrachtSerie() {
		// TODO Auto-generated method stub
		return this.serie;
	}

	public void setOpdrachtSerie(int serie) {
		// TODO Auto-generated method stub
		this.serie = serie;
	}
	
	public int getOpdrachtHerhaal() {
		// TODO Auto-generated method stub
		return this.herhaal;
	}

	public void setOpdrachtHerhaal(int herhaal) {
		// TODO Auto-generated method stub
		this.herhaal = herhaal;
	}
	
	public int getOpdrachtTrainingId() {
		// TODO Auto-generated method stub
		return this.trainingid;
	}

	public void setOpdrachtTrainingId(int trainingid) {
		// TODO Auto-generated method stub
		this.trainingid = trainingid;
	}
	
	

}
