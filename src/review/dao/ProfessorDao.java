package review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import review.model.Professor;
import review.model.Reviews;
import review.model.University;

public class ProfessorDao {
	protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static ProfessorDao instance = null;
	
	protected ProfessorDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ProfessorDao getInstance() {
		if(instance == null) {
			instance = new ProfessorDao();
		}
		return instance;
	}
	
	public List<Professor> getProfessorsFromLastName(String lastname) throws SQLException {
		List<Professor> professors = new ArrayList<Professor>();
		lastname="%"+lastname+"%";
		String selectProfessors =
			"SELECT * FROM Professor WHERE LastName like ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UniversityDao univ = new UniversityDao();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessors);
			selectStmt.setString(1, lastname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int id = results.getInt("ProfessorId");
				String firstname = results.getString("FirstName");
				String resultlastName = results.getString("LastName");
				University bachelors = univ.getUniversityById(results.getInt("Bachelors"));
				University masters = univ.getUniversityById(results.getInt("Masters"));
				University phd = univ.getUniversityById(results.getInt("Phd"));
				University teachingUniversity = univ.getUniversityById(results.getInt("TeachingUniversity"));
				int joiningYear = results.getInt("JoiningYear");
				String rank = results.getString("Rank");
				String url = results.getString("url");
				String photourl = results.getString("PhotoUrl");
				
				Professor professor = new Professor(id, firstname, resultlastName, bachelors, masters, phd, teachingUniversity, joiningYear, rank, url,photourl);
				professors.add(professor);
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
		return professors;
	}

	public List<Professor> getProfessorsFromFirstName(String firstname) throws SQLException {
		// TODO Auto-generated method stub
		firstname = "%"+firstname+"%";
		List<Professor> professors = new ArrayList<Professor>();
		String selectProfessors =
			"SELECT * FROM Professor WHERE FirstName like ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UniversityDao univ = new UniversityDao();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessors);
			selectStmt.setString(1, firstname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int id = results.getInt("ProfessorId");
				String resultFirstname = results.getString("FirstName");
				String lastName = results.getString("LastName");
				University bachelors = univ.getUniversityById(results.getInt("Bachelors"));
				University masters = univ.getUniversityById(results.getInt("Masters"));
				University phd = univ.getUniversityById(results.getInt("Phd"));
				University teachingUniversity = univ.getUniversityById(results.getInt("TeachingUniversity"));
				int joiningYear = results.getInt("JoiningYear");
				String rank = results.getString("Rank");
				String url = results.getString("url");
				String photourl = results.getString("PhotoUrl");
				
				Professor professor = new Professor(id, resultFirstname, lastName, bachelors, masters, phd, teachingUniversity, joiningYear, rank, url,photourl);
				professors.add(professor);
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
		return professors;
	}

	public List<Professor> getProfessorsFromtName(String firstname, String lastname) throws SQLException {
		// TODO Auto-generated method stub
		firstname="%"+firstname+"%";
		lastname="%"+lastname+"%";
		List<Professor> professors = new ArrayList<Professor>();
		String selectProfessors =
			"SELECT * FROM Professor WHERE LastName LIKE ? AND FirstName LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UniversityDao univ = new UniversityDao();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessors);
			selectStmt.setString(1, lastname);
			selectStmt.setString(2, firstname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int id = results.getInt("ProfessorId");
				String resultfirstname = results.getString("FirstName");
				String resultlastName = results.getString("LastName");
				University bachelors = univ.getUniversityById(results.getInt("Bachelors"));
				University masters = univ.getUniversityById(results.getInt("Masters"));
				University phd = univ.getUniversityById(results.getInt("Phd"));
				University teachingUniversity = univ.getUniversityById(results.getInt("TeachingUniversity"));
				int joiningYear = results.getInt("JoiningYear");
				String rank = results.getString("Rank");
				String url = results.getString("url");
				String photourl = results.getString("PhotoUrl");
				
				Professor professor = new Professor(id, resultfirstname, resultlastName, bachelors, masters, phd, teachingUniversity, joiningYear, rank, url,photourl);
				professors.add(professor);
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
		return professors;
	}
	
	public Professor getProfessorById(int id) throws SQLException {
		
		Professor professor;
		String selectProfessor =
			"SELECT * FROM Professor WHERE ProfessorId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UniversityDao univ = new UniversityDao();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessor);
			selectStmt.setInt(1, id);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int resultId = results.getInt("ProfessorId");
				String resultFirstname = results.getString("FirstName");
				String lastName = results.getString("LastName");
				University bachelors = univ.getUniversityById(results.getInt("Bachelors"));
				University masters = univ.getUniversityById(results.getInt("Masters"));
				University phd = univ.getUniversityById(results.getInt("Phd"));
				University teachingUniversity = univ.getUniversityById(results.getInt("TeachingUniversity"));
				int joiningYear = results.getInt("JoiningYear");
				String rank = results.getString("Rank");
				String url = results.getString("url");
				String photourl = results.getString("PhotoUrl");
				
				professor = new Professor(id, resultFirstname, lastName, bachelors, masters, phd, teachingUniversity, joiningYear, rank, url,photourl);
				return professor;
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
	public List<Professor> getProfessorsFromUniversity(int univId) throws SQLException {
		List<Professor> professors = new ArrayList<Professor>();
		String selectProfessors =
			"SELECT * FROM Professor WHERE TeachingUniversity=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		UniversityDao univ = new UniversityDao();
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectProfessors);
			selectStmt.setInt(1, univId);
			results = selectStmt.executeQuery();
			while(results.next()) {
				int id = results.getInt("ProfessorId");
				String firstname = results.getString("FirstName");
				String resultlastName = results.getString("LastName");
				University bachelors = univ.getUniversityById(results.getInt("Bachelors"));
				University masters = univ.getUniversityById(results.getInt("Masters"));
				University phd = univ.getUniversityById(results.getInt("Phd"));
				University teachingUniversity = univ.getUniversityById(results.getInt("TeachingUniversity"));
				int joiningYear = results.getInt("JoiningYear");
				String rank = results.getString("Rank");
				String url = results.getString("url");
				String photourl = results.getString("PhotoUrl");
				
				Professor professor = new Professor(id, firstname, resultlastName, bachelors, masters, phd, teachingUniversity, joiningYear, rank, url,photourl);
				professors.add(professor);
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
		return professors;
	}
	
	
	public Professor create(Professor professor) throws SQLException {
		String insertProfessor = "INSERT INTO Professor(FirstName,LastName,Bachelors,Masters,PHD,TeachingUniversity,"+
				"JoiningYear, Rank, Url, PhotoUrl) VALUES(?,?,?,?,?,?,?,?,?,?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertProfessor);
			insertStmt.setString(1, professor.getFirstName());
			insertStmt.setString(2, professor.getLastName());
			if(professor.getBachelors()==null) 
				insertStmt.setNull(3,Types.INTEGER); 
			else 
				insertStmt.setInt(3, professor.getBachelors().getUniversityId());
			if(professor.getMasters()==null) 
				insertStmt.setNull(4,Types.INTEGER); 
			else 
			insertStmt.setInt(4, professor.getMasters().getUniversityId());
			
			if(professor.getPhd()==null) 
				insertStmt.setNull(5,Types.INTEGER); 
			else 
				insertStmt.setInt(5, professor.getPhd().getUniversityId());
			
			
			insertStmt.setInt(6, professor.getTeachingUniversity().getUniversityId());
			
			if(professor.getJoiningYear()==0) 
				insertStmt.setNull(7,Types.INTEGER); 
			else 
				insertStmt.setInt(7, professor.getJoiningYear());
			
			insertStmt.setString(8, professor.getRank());
			insertStmt.setString(9, professor.getUrl());
			insertStmt.setString(10, professor.getPhotoUrl());
			
			insertStmt.executeUpdate();
		
			return professor;
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

	public void delete(int id) throws SQLException {
		String deleteCompany = "DELETE FROM Professor WHERE ProfessorId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCompany);
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
