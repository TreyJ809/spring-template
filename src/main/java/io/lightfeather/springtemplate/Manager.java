package io.lightfeather.springtemplate;

import java.util.ArrayList;
import java.util.List;

public class Manager {
	private String jurisdiction;
	private String lastName;
	private String firstName;

	public Manager () {
		//Create Manager where values will be set later
	}

	public Manager (String j, String l, String f) {
		//Create Manager and set Jurisdiction (j), LastName (l), and FirstName (f)
		setJurisdiction(j);
		setLastname(l);
		setFirstName(f);
	}

	public String getJurisdiction()
	{
		return jurisdiction;
	}

	public void setJurisdiction(String j) {
		jurisdiction = j;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastname(String l) {
		lastName = l;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String f) {
		firstName = f;
	}

	public String toString() {
		//format for based on specifications. This will also help with sorting
		return getJurisdiction() + " - " + getLastName() + ", " + getFirstName();
	}
}