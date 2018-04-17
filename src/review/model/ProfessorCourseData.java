package review.model;

public class ProfessorCourseData {
	protected int id;
	protected Courses course;
	protected Professor professor;
	
	public ProfessorCourseData(int id, Courses course, Professor professor) {
		super();
		this.id = id;
		this.course = course;
		this.professor = professor;
	}

	public ProfessorCourseData(Courses course, Professor professor) {
		super();
		this.course = course;
		this.professor = professor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	
	
}
