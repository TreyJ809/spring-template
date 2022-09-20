package io.lightfeather.springtemplate;

import java.util.ArrayList;
import java.util.List;

public class PersonalInfo {
	private String supervisor;
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String email;

	public PersonalInfo () {
		//Create PersonalInfo where values will be set later
	}

	public PersonalInfo (String s, String l, String f) {
		//Create PersonalInfo and set required fields (supervisor, lastName, firstName)
		setSupervisor(s);
		setLastName(l);
		setFirstName(f);
	}

	public String getSupervisor()
	{
		return supervisor;
	}

	public void setSupervisor(String s) {
		supervisor = s;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String l) {
		lastName = l;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String f) {
		firstName = f;
	}

	public String getPhoneNumber()
	{
		return phoneNumber;
	}

	public void setPhoneNumber(String p) {
		phoneNumber = p;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String e) {
		email = e;
	}

}