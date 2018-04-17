package review.dao;

public class AdminDao extends LoginDao {
	
	// Single pattern: instantiation is limited to one object.
	private static AdminDao instance = null;
	
	protected AdminDao() {
		super();
	}
	
	public static AdminDao getInstance() {
		if(instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
}
