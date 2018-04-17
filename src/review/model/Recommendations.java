package review.model;

public class Recommendations {
	protected int recommendationId;
	protected Courses course;
	protected Students student;
	
	public Recommendations(int recommendationId, Courses course, Students student) {
		super();
		this.recommendationId = recommendationId;
		this.course = course;
		this.student = student;
	}

	public Recommendations(Courses course, Students student) {
		super();
		this.course = course;
		this.student = student;
	}

	public int getRecommendationId() {
		return recommendationId;
	}

	public void setRecommendationId(int recommendationId) {
		this.recommendationId = recommendationId;
	}

	public Courses getCourse() {
		return course;
	}

	public void setCourse(Courses course) {
		this.course = course;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}
	
	
	
}
