package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import review.dao.ConnectionManager;
import review.model.*;
import review.model.Login.Roles;

public class LoginDao {
	protected ConnectionManager connectionManager;
	// Single pattern: instantiation is limited to one object.
	private static LoginDao instance = null;
	
	protected LoginDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static LoginDao getInstance() {
		if(instance == null) {
			instance = new LoginDao();
		}
		return instance;
	}
	
	public Login getLoginDetails(String userName) throws SQLException {
		String selectPerson = "SELECT UserName,Password,Role FROM Login WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setString(1, userName);
			
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				Roles role = Roles.valueOf(results.getString("Role"));
				Login login = new Login(resultUserName, password, role);
				return login;
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

	public Login create(Login login) throws SQLException {
		// TODO Auto-generated method stub
		String insertUser = "INSERT INTO Login(UserName,Password,Role) VALUES(?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			
			insertStmt.setString(1, login.getUserName());
			insertStmt.setString(2, login.getPassword());
			insertStmt.setString(3, login.getRoles().name());
			
			insertStmt.executeUpdate();
		
			return login;
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
