package nl.moukail.sportsquare.model;

import java.io.Serializable;

public class Zaal implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String adress;
	private String longitude;
	private String latitude;
	private String city;
	private int visitors;
	
	public String getZaalName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public void setZaalName(String name) {
		// TODO Auto-generated method stub
		this.name = name;
	}
	
	public String getZaalAdress() {
		// TODO Auto-generated method stub
		return this.adress;
	}
	
	public void setZaalAdress(String adress) {
		// TODO Auto-generated method stub
		this.adress = adress;
	}

	public int getZaalId() {
		// TODO Auto-generated method stub
		return this.id;
	}
	public void setZaalId(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public String getZaalLatitude() {
		// TODO Auto-generated method stub
		return this.latitude;
	}
	
	public void setZaalLatitude(String latitude) {
		// TODO Auto-generated method stub
		this.latitude = latitude;
	}

	public String getZaalLongitude() {
		// TODO Auto-generated method stub
		return this.longitude;
	}
	
	public void setZaalLongitude(String longitude) {
		// TODO Auto-generated method stub
		this.longitude = longitude;
	}

	public void setZaalCity(String city) {
		// TODO Auto-generated method stub
		this.city = city;
	}

	public String getZaalCity() {
		// TODO Auto-generated method stub
		return this.city;
	}

	public void setZaalVisitors(int visitors) {
		// TODO Auto-generated method stub
		this.visitors = visitors;
	}
	public int getZaalVisitors() {
		// TODO Auto-generated method stub
		return this.visitors;
	}
}
