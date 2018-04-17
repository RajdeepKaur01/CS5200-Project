package review.model;

public class Professor {
	protected int professorId;
	protected String firstName;
	protected String lastName;
	protected University bachelors;
	protected University masters;
	protected University phd;
	protected University teachingUniversity;
	protected int joiningYear;
	protected String rank;
	protected String url;
	protected String photoUrl;
	
	public Professor(int professorId, String firstName, String lastName, University bachelors, University masters,
			University phd, University teachingUniversity, int joiningYear, String rank, String url, String photoUrl) {
		super();
		this.professorId = professorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.bachelors = bachelors;
		this.masters = masters;
		this.phd = phd;
		this.teachingUniversity = teachingUniversity;
		this.joiningYear = joiningYear;
		this.rank = rank;
		this.url = url;
		this.photoUrl = photoUrl;
	}

	public Professor(String firstName, String lastName, University bachelors, University masters, University phd,
			University teachingUniversity, int joiningYear, String rank, String url, String photoUrl) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.bachelors = bachelors;
		this.masters = masters;
		this.phd = phd;
		this.teachingUniversity = teachingUniversity;
		this.joiningYear = joiningYear;
		this.rank = rank;
		this.url = url;
		this.photoUrl = photoUrl;
	}

	public Professor(int professorId) {
		super();
		this.professorId = professorId;
	}

	public int getProfessorId() {
		return professorId;
	}

	public void setProfessorId(int professorId) {
		this.professorId = professorId;
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

	public University getBachelors() {
		return bachelors;
	}

	public void setBachelors(University bachelors) {
		this.bachelors = bachelors;
	}

	public University getMasters() {
		return masters;
	}

	public void setMasters(University masters) {
		this.masters = masters;
	}

	public University getPhd() {
		return phd;
	}

	public void setPhd(University phd) {
		this.phd = phd;
	}

	public University getTeachingUniversity() {
		return teachingUniversity;
	}

	public void setTeachingUniversity(University teachingUniversity) {
		this.teachingUniversity = teachingUniversity;
	}

	public int getJoiningYear() {
		return joiningYear;
	}

	public void setJoiningYear(int joiningYear) {
		this.joiningYear = joiningYear;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
}
