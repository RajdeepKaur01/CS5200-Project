package review.model;

public class Comments {
	protected int commentId;
	protected Students student;
	protected Reviews review;
	protected String commentDescription;
	protected boolean showName;
	
	public Comments(int commentId, Students student, Reviews review, String commentDescription, boolean showName) {
		super();
		this.commentId = commentId;
		this.student = student;
		this.review = review;
		this.commentDescription = commentDescription;
		this.showName = showName;
	}

	public Comments(Students student, Reviews review, String commentDescription, boolean showName) {
		super();
		this.student = student;
		this.review = review;
		this.commentDescription = commentDescription;
		this.showName = showName;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Students getStudent() {
		return student;
	}

	public void setStudent(Students student) {
		this.student = student;
	}

	public Reviews getReview() {
		return review;
	}

	public void setReview(Reviews review) {
		this.review = review;
	}

	public String getCommentDescription() {
		return commentDescription;
	}

	public void setCommentDescription(String commentDescription) {
		this.commentDescription = commentDescription;
	}

	public boolean isShowName() {
		return showName;
	}

	public void setShowName(boolean showName) {
		this.showName = showName;
	}
	
	
	
}
