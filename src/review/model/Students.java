package review.model;

import java.time.Year;

public class Students extends Login {
	protected String firstName;
	protected String lastName;
	protected University university;
	protected int year;
	
	public Students(String userName, String password, Roles role, String firstName, String lastName,
			University university, int year) {
		super(userName, password, role);
		this.firstName = firstName;
		this.lastName = lastName;
		this.university = university;
		this.year = year;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	
}
