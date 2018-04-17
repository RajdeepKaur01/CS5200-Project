package review.dao;

public class RecommendationsDao {
protected ConnectionManager connectionManager;
	
	// Single pattern: instantiation is limited to one object.
	private static RecommendationsDao instance = null;
	
	protected RecommendationsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static RecommendationsDao getInstance() {
		if(instance == null) {
			instance = new RecommendationsDao();
		}
		return instance;
	}
}
