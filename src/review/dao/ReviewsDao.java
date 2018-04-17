package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import review.model.Courses;
import review.model.Login;
import review.model.Professor;
import review.model.Reviews;
import review.model.Students;
import review.model.University;
import review.model.Reviews.CourseWork;
import review.model.Reviews.Difficulty;

public class ReviewsDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ReviewsDao instance = null;
	
	protected ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ReviewsDao getInstance() {
		if(instance == null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	

	public List<Reviews> getReviewsByCourseId(int id) throws SQLException {
		List<Reviews> reviews = new ArrayList<>();
		String selectReviews = "SELECT * FROM Reviews WHERE CourseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CoursesDao courseDao = CoursesDao.getInstance();
		StudentsDao studentDao = StudentsDao.getInstance();
		ProfessorDao professorDao = ProfessorDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, id);
			
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				Students student = studentDao.getStudentyByUsername(results.getString("UserName"));
				Professor professor = professorDao.getProfessorById(results.getInt("ProfessorId"));
				Courses course = courseDao.getCourseById(results.getInt("CourseId"));
				String reviewDescription = results.getString("ReviewDescription");
				Difficulty difficulty = null;
				if(results.getString("Difficulty")!=null)
					difficulty = Difficulty.valueOf(results.getString("Difficulty"));
				CourseWork courseWork = null;
				if(results.getString("CourseWork")!=null)
					courseWork = CourseWork.valueOf(results.getString("CourseWork"));
				int yearAttended = results.getInt("YearAttended");
				boolean showName = results.getBoolean("ShowName");
				int helpful = results.getInt("Helpful");
				int notHelpful = results.getInt("NotHelpful");
				Reviews review = new Reviews(reviewId, student, professor, course, reviewDescription, difficulty, courseWork, yearAttended, showName, helpful, notHelpful);
				reviews.add(review);
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
		return reviews;
	}
	
	public Reviews getReviewById(int id) throws SQLException {
		String selectReviews = "SELECT * FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CoursesDao courseDao = CoursesDao.getInstance();
		StudentsDao studentDao = StudentsDao.getInstance();
		ProfessorDao professorDao = ProfessorDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, id);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int reviewId = results.getInt("ReviewId");
				Students student = studentDao.getStudentyByUsername(results.getString("UserName"));
				Professor professor = professorDao.getProfessorById(results.getInt("ProfessorId"));
				Courses course = courseDao.getCourseById(results.getInt("CourseId"));
				String reviewDescription = results.getString("ReviewDescription");
				Difficulty difficulty = null;
				if(results.getString("Difficulty")!=null)
					difficulty = Difficulty.valueOf(results.getString("Difficulty"));
				CourseWork courseWork = null;
				if(results.getString("CourseWork")!=null)
					courseWork = CourseWork.valueOf(results.getString("CourseWork"));
				int yearAttended = results.getInt("YearAttended");
				boolean showName = results.getBoolean("ShowName");
				int helpful = results.getInt("Helpful");
				int notHelpful = results.getInt("NotHelpful");
				Reviews review = new Reviews(reviewId, student, professor, course, reviewDescription, difficulty, courseWork, yearAttended, showName, helpful, notHelpful);
				return review;
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
		return null;
	}
	
	
	public int getProfessorIdByReview(int id) throws SQLException {
		String selectReviews = "SELECT ProfessorId FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CoursesDao courseDao = CoursesDao.getInstance();
		StudentsDao studentDao = StudentsDao.getInstance();
		ProfessorDao professorDao = ProfessorDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, id);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				return results.getInt("ProfessorId");
				
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
		return 0;
	}
	
	
	public Reviews create(Reviews review) throws SQLException {
		String insertReview = "INSERT INTO Reviews(UserName,ProfessorId,CourseId,ReviewDescription,Difficulty,CourseWork,"+
				"YearAttended, ShowName, Helpful, NotHelpful) VALUES(?,?,?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			
			insertStmt.setString(1, review.getStudent().getUserName());
			insertStmt.setInt(2, review.getProfessor().getProfessorId());
			insertStmt.setInt(3, review.getCourse().getCourseId());
			insertStmt.setString(4, review.getReviewDescription());
			insertStmt.setString(5, review.getDifficulty().name());
			insertStmt.setString(6, review.getCourseWork().name());
			insertStmt.setInt(7, review.getYearAttended());
			insertStmt.setBoolean(8, review.isShowName());
			insertStmt.setInt(9, review.getHelpful());
			insertStmt.setInt(10, review.getNotHelpful());
			
			insertStmt.executeUpdate();
		
			return review;
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

	public List<Reviews> getReviewsByProfAndCourseId(int courseId, int professorId) throws SQLException {
		// TODO Auto-generated method stub
		List<Reviews> reviews = new ArrayList<>();
		String selectReviews = "SELECT * FROM Reviews WHERE CourseId=? AND ProfessorId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CoursesDao courseDao = CoursesDao.getInstance();
		StudentsDao studentDao = StudentsDao.getInstance();
		ProfessorDao professorDao = ProfessorDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, courseId);
			selectStmt.setInt(2, professorId);
			
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int reviewId = results.getInt("ReviewId");
				Students student = studentDao.getStudentyByUsername(results.getString("UserName"));
				Professor professor = professorDao.getProfessorById(results.getInt("ProfessorId"));
				Courses course = courseDao.getCourseById(results.getInt("CourseId"));
				String reviewDescription = results.getString("ReviewDescription");
				Difficulty difficulty = null;
				if(results.getString("Difficulty")!=null)
					difficulty = Difficulty.valueOf(results.getString("Difficulty"));
				CourseWork courseWork = null;
				if(results.getString("CourseWork")!=null)
					courseWork = CourseWork.valueOf(results.getString("CourseWork"));
				int yearAttended = results.getInt("YearAttended");
				boolean showName = results.getBoolean("ShowName");
				int helpful = results.getInt("Helpful");
				int notHelpful = results.getInt("NotHelpful");
				Reviews review = new Reviews(reviewId, student, professor, course, reviewDescription, difficulty, courseWork, yearAttended, showName, helpful, notHelpful);
				reviews.add(review);
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
		return reviews;
	}

	
	public void updateLikeCount(int reviewId) throws SQLException {

		String selectReviews = "UPDATE Reviews SET Helpful=Helpful+1 WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, reviewId);
			
			selectStmt.executeUpdate();
			
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
		}
	
	}
	
	public void updateDislikeCount(int reviewId) throws SQLException {

		String selectReviews = "UPDATE Reviews SET NotHelpful=NotHelpful+1 WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReviews);
			selectStmt.setInt(1, reviewId);
			
			selectStmt.executeUpdate();
			
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
		}
	
	}
	
	public void delete(int id) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReview);
			deleteStmt.setInt(1, id);
			deleteStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}

	}
}
