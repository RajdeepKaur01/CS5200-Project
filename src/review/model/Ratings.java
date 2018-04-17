package review.model;

public class Ratings {
	protected int ratingId;
	protected Courses course;
	protected Professor professor;
	protected double rating;
	
	public Ratings(int ratingId, Courses course, Professor professor, double rating) {
		super();
		this.ratingId = ratingId;
		this.course = course;
		this.professor = professor;
		this.rating = rating;
	}

	public Ratings(Courses course, Professor professor, double rating) {
		super();
		this.course = course;
		this.professor = professor;
		this.rating = rating;
	}

	public int getRatingId() {
		return ratingId;
	}

	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}
	
	
	
}
