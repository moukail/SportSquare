package nl.moukail.sportsquare.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Dag implements Serializable{
	private int id;
	private String dagname;
	ArrayList<Training> m_training;
	
	public Dag(){
		this.m_training = new ArrayList<Training>();
	}
	public void setDagId(int id){
		this.id = id;
	}
	
	public int getDagId(){
		return this.id;
	}
	
	public String getDagName(){
		return this.dagname;
	}
	
	
	public void setDagName(String dagname){
		this.dagname = dagname;
	}
	
	
	
	public Training getTraining(int position){
		return this.m_training.get(position);
	}

	public void addTraining(Training training){
		this.m_training.add(training);		
	}

	public ArrayList<Training> getTrainingen() {
		// TODO Auto-generated method stub
		return this.m_training;
	}
	public void setTrainingen(ArrayList<Training> m_trainingen) {
		// TODO Auto-generated method stub
		this.m_training = m_trainingen;
	}
	
}
