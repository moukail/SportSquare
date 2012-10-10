package nl.moukail.sportsquare.model;

import java.io.Serializable;

public class Person implements Serializable {
	int id;
	String firstName;
	String lastName;
	String lastVisit;
	String zaal_name;

	public Person() {
		
	}

	public int getPersonId() {
		return this.id;
	}

	public void setPersonId(int id) {
		this.id = id;
	}

	public String getPersonFirstName() {
		return this.firstName;
	}

	public void setPersonFirstName(String firstname) {
		this.firstName = firstname;
	}

	public String getPersonLastName() {
		return this.lastName;
	}

	public void setPersonLastName(String name) {
		this.lastName = name;
	}

	public String getLastVisit() {
		return this.lastVisit;
	}

	public void setPersonLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	public String getPersonZaal() {
		// TODO Auto-generated method stub
		return this.zaal_name;
	}
	public void setPersonZaal(String zaal){
		this.zaal_name = zaal;
	}
}