package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import review.model.University;

public class UniversityDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static UniversityDao instance = null;
	
	protected UniversityDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UniversityDao getInstance() {
		if(instance == null) {
			instance = new UniversityDao();
		}
		return instance;
	}
	
	public University getUniversityById(int id) throws SQLException {
		String selectPerson = "SELECT * FROM University WHERE UniversityId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPerson);
			selectStmt.setInt(1, id);
		
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultId = results.getInt("UniversityId");
				String name = results.getString("Name");
				String location	 = results.getString("Location");
				University univ = new University(resultId, name, location);
				return univ;
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
	
	
	public List<University> getAllUniversities() throws SQLException {
		List<University> luniv = new ArrayList<University>();
		String selectUniv = "SELECT * FROM University";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUniv);
			results = selectStmt.executeQuery();
			
			while(results.next()) {
				int resultId = results.getInt("UniversityId");
				String name = results.getString("Name");
				String location	 = results.getString("Location");
				University univ = new University(resultId, name, location);
				luniv.add(univ);
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
		return luniv;
	}
}
