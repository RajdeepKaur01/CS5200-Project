package review.model;

public class Login {
	
	protected String userName;
	protected String password;
	protected Roles role;
	
	public enum Roles {
		ADMIN, STUDENT;
	}
	
	public Login(String userName, String password, Roles role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public Roles getRoles() {
		return role;
	}

	public void setRoles(Roles role) {
		this.role = role;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
