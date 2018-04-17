package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import review.model.Courses;
import review.model.Login;
import review.model.Students;
import review.model.University;

public class StudentsDao extends LoginDao {

	// Single pattern: instantiation is limited to one object.
	private static StudentsDao instance = null;
	
	protected StudentsDao() {
		super();
	}
	
	public static StudentsDao getInstance() {
		if(instance == null) {
			instance = new StudentsDao();
		}
		return instance;
	}
	

	public Students getStudentyByUsername(String username) throws SQLException {
		String selectPerson = "SELECT * FROM Students WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UniversityDao univDao = UniversityDao.getInstance();
		LoginDao loginDao = LoginDao.getInstance();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, username);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String userName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				University university = univDao.getUniversityById(results.getInt("UniversityId"));
				int graduationYear = results.getInt("GraduationYear");
				Login login = loginDao.getLoginDetails(username);
				
				Students student = new Students(userName, login.getPassword(), login.getRoles(), firstName, lastName, university, graduationYear);
				return student;
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
	
	

	public Students create(Students student) throws SQLException {
		LoginDao login = new LoginDao();
		login.create(new Login(student.getUserName(),student.getPassword(),student.getRoles()));
		String insertUser = "INSERT INTO Students(UserName,FirstName,LastName,UniversityId,GraduationYear) VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			
			insertStmt.setString(1, student.getUserName());
			insertStmt.setString(2, student.getFirstName());
			insertStmt.setString(3, student.getLastName());
			insertStmt.setInt(4, student.getUniversity().getUniversityId());
			insertStmt.setInt(5, student.getYear());
			
			insertStmt.executeUpdate();
		
			return student;
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
