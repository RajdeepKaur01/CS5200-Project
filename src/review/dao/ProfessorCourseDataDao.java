package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.Courses;
import review.model.Professor;
import review.model.University;

public class ProfessorCourseDataDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ProfessorCourseDataDao instance = null;
	
	protected ProfessorCourseDataDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ProfessorCourseDataDao getInstance() {
		if(instance == null) {
			instance = new ProfessorCourseDataDao();
		}
		return instance;
	}

	public List<Courses> getCoursesFromProfessor(int professorId) throws SQLException {
		
		List<Courses> courses = new ArrayList<Courses>();
		
		String selectProfessors =
			"SELECT * FROM ProfessorCourseData WHERE ProfessorId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CoursesDao coursesDao = CoursesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessors);
			selectStmt.setInt(1, professorId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				Courses course =coursesDao.getCourseById(results.getInt("CourseId"));
				courses.add(course);
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
		return courses;
	}

	public boolean checkCourseProfessorJoin(int professorId, int courseId) throws SQLException {
		
		String selectProfessors =
			"SELECT * FROM ProfessorCourseData WHERE ProfessorId=? and CourseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		CoursesDao coursesDao = CoursesDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessors);
			selectStmt.setInt(1, professorId);
			selectStmt.setInt(2, courseId);
			results = selectStmt.executeQuery();
			if(results.next()) {
				return true;
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
		}
		return false;
	}

	public void createPCJoin(int professorId, int courseId) throws SQLException {
		String insertReview = "INSERT INTO ProfessorCourseData(ProfessorId,CourseId) VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReview);
			
			insertStmt.setInt(1, professorId);
			insertStmt.setInt(2, courseId);
			insertStmt.executeUpdate();
		
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
