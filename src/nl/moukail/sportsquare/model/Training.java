package nl.moukail.sportsquare.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Training implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6817958560039905658L;
	private int id;
	private int dagnummer;
	private ArrayList<Opdracht> m_opdracht;
	
	public Training(){
		this.m_opdracht = new ArrayList<Opdracht>();
	}
	
	public void setTrainingId(int id){
		this.id = id;
	}
	
	public int getTrainingId(){
		return this.id;
	}
	
	public int getDagNummer(){
		return this.dagnummer;
	}
	
	
	public void setDagNummer(int dagnummer){
		this.dagnummer = dagnummer;
	}
	
	
	public Opdracht getOpdracht(int position){
		return this.m_opdracht.get(position); 
	}
	
	public void addOpdracht(Opdracht opdracht){
		this.m_opdracht.add(opdracht);
	}

	public ArrayList<Opdracht> getOpdrachten() {
		// TODO Auto-generated method stub
		return this.m_opdracht;
	}
	

}
