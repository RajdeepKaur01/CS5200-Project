package review.model;

public class Courses {
	protected int courseId;
	protected String courseName;
	protected String courseCode;
	protected University university;
	protected String department;
	
	public Courses(int courseId, String courseName, String courseCode, University university, String department) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.university = university;
		this.department = department;
	}
	
	public Courses(String courseName, String courseCode, University university, String department) {
		super();
		this.courseName = courseName;
		this.courseCode = courseCode;
		this.university = university;
		this.department = department;
	}
	
	public Courses(int courseId) {
		super();
		this.courseId = courseId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseCode() {
		return courseCode;
	}
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	public University getUniversity() {
		return university;
	}
	public void setUniversity(University university) {
		this.university = university;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
}
