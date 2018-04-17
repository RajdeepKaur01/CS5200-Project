package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.Courses;
import review.model.Login;
import review.model.University;
import review.model.Login.Roles;

public class CoursesDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static CoursesDao instance = null;
	
	protected CoursesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static CoursesDao getInstance() {
		if(instance == null) {
			instance = new CoursesDao();
		}
		return instance;
	}

	public Courses getCourseById(int id) throws SQLException {
		String selectPerson = "SELECT * FROM Courses WHERE CourseId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UniversityDao univDao = UniversityDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, id);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int courseId = results.getInt("CourseId");
				String courseName = results.getString("CourseName");
				String courseCode = results.getString("CourseCode");
				University university = univDao.getUniversityById(results.getInt("UniversityId"));
				String department = results.getString("Department");
				Courses course = new Courses(courseId, courseName, courseCode, university, department);
				return course;
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
	
	public List<Courses> getCourseByUniversityId(int id) throws SQLException {
		List<Courses> courses=new ArrayList<>();
		String selectPerson = "SELECT * FROM Courses WHERE UniversityId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, id);
			
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int courseId = results.getInt("CourseId");
				String courseName = results.getString("CourseName");
				String courseCode = results.getString("CourseCode");
				University university = null;
				String department = results.getString("Department");
				Courses course = new Courses(courseId, courseName, courseCode, university, department);
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
	
	public void createNewCourse(String courseCode,String courseName,String department, int universityId) throws SQLException {
		String insertCourse = "INSERT INTO Courses(CourseCode,CourseName,Department,UniversityId) VALUES(?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertCourse);
			
			insertStmt.setString(1, courseCode);
			insertStmt.setString(2, courseName);
			insertStmt.setString(3, department);
			insertStmt.setInt(4, universityId);
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
