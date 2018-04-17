package review.model;

import java.time.Year;

public class Reviews {
	
	public enum Difficulty {
		HARD, MEDIUM, EASY
	}
	public enum CourseWork {
		HEAVY, MEDIUM, LIGHT
	}
	
	protected int reviewId;
	protected Students student;
	protected Professor professor;
	protected Courses course;
	protected String reviewDescription;
	protected Difficulty difficulty;
	protected CourseWork courseWork;
	protected int yearAttended;
	protected boolean showName;
	protected int helpful;
	protected int notHelpful;
	
	public Reviews(int reviewId, Students student, Professor professor, Courses course, String reviewDescription,
			Difficulty difficulty, CourseWork courseWork, int yearAttended, boolean showName, int helpful,
			int notHelpful) {
		super();
		this.reviewId = reviewId;
		this.student = student;
		this.professor = professor;
		this.course = course;
		this.reviewDescription = reviewDescription;
		this.difficulty = difficulty;
		this.courseWork = courseWork;
		this.yearAttended = yearAttended;
		this.showName = showName;
		this.helpful = helpful;
		this.notHelpful = notHelpful;
	}

	public Reviews(Students student, Professor professor, Courses course, String reviewDescription,
			Difficulty difficulty, CourseWork courseWork, int yearAttended, boolean showName, int helpful,
			int notHelpful) {
		super();
		this.student = student;
		this.professor = professor;
		this.course = course;
		this.reviewDescription = reviewDescription;
		this.difficulty = difficulty;
		this.courseWork = courseWork;
		this.yearAttended = yearAttended;
		this.showName = showName;
		this.helpful = helpful;
		this.notHelpful = notHelpful;
	}

	public Reviews(int reviewId) {
		super();
		this.reviewId = reviewId;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public String getReviewDescription() {
		return reviewDescription;
	}

	public void setReviewDescription(String reviewDescription) {
		this.reviewDescription = reviewDescription;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public CourseWork getCourseWork() {
		return courseWork;
	}

	public void setCourseWork(CourseWork courseWork) {
		this.courseWork = courseWork;
	}

	public int getYearAttended() {
		return yearAttended;
	}

	public void setYearAttended(int yearAttended) {
		this.yearAttended = yearAttended;
	}

	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}

	public int getHelpful() {
		return helpful;
	}

	public void setHelpful(int helpful) {
		this.helpful = helpful;
	}

	public int getNotHelpful() {
		return notHelpful;
	}

	public void setNotHelpful(int notHelpful) {
		this.notHelpful = notHelpful;
	}
	
	
	
}
