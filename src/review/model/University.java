package review.model;

public class University {
	protected int universityId;
	protected String name;
	protected String location;
	
	public University(int universityId, String name, String location) {
		super();
		this.universityId = universityId;
		this.name = name;
		this.location = location;
	}

	public University(String name, String location) {
		super();
		this.name = name;
		this.location = location;
	}

	public University(int universityId) {
		super();
		this.universityId = universityId;
	}

	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
	
	
}
