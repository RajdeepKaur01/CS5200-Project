package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import review.model.Professor;
import review.model.University;

public class RatingsDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RatingsDao instance = null;
	
	protected RatingsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static RatingsDao getInstance() {
		if(instance == null) {
			instance = new RatingsDao();
		}
		return instance;
	}
	
	public double getAvgRatingByProfessorId(int id) throws SQLException {
		String selectRating =
				"SELECT TRUNCATE(AVG(Rating),1) AS Rating from Ratings where ProfessorId=?;";
			Connection connection = null;
			PreparedStatement selectStmt = null;
			ResultSet results = null;
			UniversityDao univ = new UniversityDao();
			try {
				connection = connectionManager.getConnection();
				selectStmt = connection.prepareStatement(selectRating);
				selectStmt.setInt(1, id);
				results = selectStmt.executeQuery();
				while(results.next()) {
					return results.getDouble("Rating");
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
			return 0.0;
	}
}
