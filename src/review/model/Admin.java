package review.model;

public class Admin extends Login {
	protected String firstName;
	protected String lastName;
	
	public Admin(String userName, String password, Roles role, String firstName, String lastName) {
		super(userName, password, role);
		this.firstName = firstName;
		this.lastName = lastName;
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
	
	
	
	
	
}
