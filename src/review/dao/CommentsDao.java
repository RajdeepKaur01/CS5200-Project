package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.Comments;
import review.model.Professor;
import review.model.Reviews;
import review.model.Students;
import review.model.University;

public class CommentsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CommentsDao instance = null;
	
	protected CommentsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static CommentsDao getInstance() {
		if(instance == null) {
			instance = new CommentsDao();
		}
		return instance;
	}
	
	public List<Comments> getCommentsByReviewId(int id) throws SQLException {
		List<Comments> comments = new ArrayList<Comments>();
		
		String selectProfessors =
			"SELECT * FROM Comments WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		ReviewsDao reviewDao=ReviewsDao.getInstance();
		StudentsDao studentDao = StudentsDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessors);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int commentId = results.getInt("CommentId");
				Students student = studentDao.getStudentyByUsername(results.getString("UserName"));
				Reviews review = reviewDao.getReviewById(results.getInt("ReviewId"));
				String description = results.getString("CommentDescription");
				boolean showname = results.getBoolean("ShowName");
				Comments comment = new Comments(commentId, student, review, description, showname);
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return comments;
	}

	

	public Comments create(Comments comment) throws SQLException {
		String insertReview = "INSERT INTO Comments(UserName,ReviewId,CommentDescription, ShowName) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			
			insertStmt.setString(1, comment.getStudent().getUserName());
			insertStmt.setInt(2, comment.getReview().getReviewId());
			insertStmt.setString(3, comment.getCommentDescription());
			insertStmt.setBoolean(4, comment.isShowName());
			
			
			insertStmt.executeUpdate();
		
			return comment;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
		}
		
	}

	
}
