package review.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import review.dao.CommentsDao;
import review.dao.ProfessorCourseDataDao;
import review.dao.ProfessorDao;
import review.dao.RatingsDao;
import review.dao.ReviewsDao;
import review.model.Comments;
import review.model.Courses;
import review.model.Professor;
import review.model.Reviews;

@WebServlet("/professorreview")
public class ProfessorReview extends HttpServlet {
	protected ProfessorDao professorDao ;
	protected ProfessorCourseDataDao pcDao;
	protected ReviewsDao reviewDao;
	protected RatingsDao ratingDao;
	
	@Override
	public void init() throws ServletException {
		professorDao = ProfessorDao.getInstance();
		pcDao = ProfessorCourseDataDao.getInstance();
		reviewDao = ReviewsDao.getInstance();
		ratingDao = RatingsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
	
		Map<String, String> messages = new HashMap<String, String>();
		Map<Integer, List<Reviews>> reviews = new HashMap<Integer, List<Reviews>>();
		Map<Integer, Courses> courseMap = new HashMap<Integer, Courses>();
		req.setAttribute("messages", messages);
		int professorId = Integer.parseInt(req.getParameter("id"));
		req.setAttribute("deleted", req.getParameter("deleted"));
		Professor professor;
		List<Courses> courses;
		String hash="#";
		double rating;
		
		try {
			//Retrieve Professor Details
			professor = professorDao.getProfessorById(professorId);
        	
        	//Retrieve Courses he teach
        	courses = pcDao.getCoursesFromProfessor(professorId);
        	
        	//Retrieve reviews for each Course
        	for(Courses c: courses){
        		List<Reviews> listreviews = reviewDao.getReviewsByProfAndCourseId(c.getCourseId(),professor.getProfessorId());
        		reviews.put(c.getCourseId(), listreviews);
        		courseMap.put(c.getCourseId(), c);
        	}
        	
        	// Retrieve Rating for professor
        	rating = ratingDao.getAvgRatingByProfessorId(professorId);
        	
        	//get session user
    		HttpSession session=req.getSession(false);  
    		if(session!=null){  
    			review.model.Login user = (review.model.Login) session.getAttribute("user");
    			req.setAttribute("user", user);
    		}
    			
        	req.setAttribute("rating", rating);
        	req.setAttribute("hash", hash);
        	req.setAttribute("star", "star");
        	req.setAttribute("professor", professor);
        	req.setAttribute("courses", courses);
        	req.setAttribute("reviews", reviews);
        	req.setAttribute("courseMap", courseMap);
        	req.setAttribute("session", session);
        	req.getRequestDispatcher("/ProfessorReview.jsp").forward(req, resp);
        	
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } 

	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("PROFESSOR POST");
		System.out.println("PR POST"+req.getParameter("id"));
		req.setAttribute("deleted", req.getParameter("deleted"));
		Map<String, String> messages = new HashMap<String, String>();
		Map<Integer, List<Reviews>> reviews = new HashMap<Integer, List<Reviews>>();
		Map<Integer, Courses> courseMap = new HashMap<Integer, Courses>();
		req.setAttribute("messages", messages);
		int professorId = Integer.parseInt(req.getParameter("id"));
		Professor professor;
		List<Courses> courses;
		String hash="#";
		double rating;
		
		try {
			//Retrieve Professor Details
        	professor = professorDao.getProfessorById(professorId);
        	
        	//Retrieve Courses he teach
        	courses = pcDao.getCoursesFromProfessor(professorId);
        	
        	//Retrieve reviews for each Course
        	for(Courses c: courses){
        		List<Reviews> listreviews = reviewDao.getReviewsByProfAndCourseId(c.getCourseId(),professor.getProfessorId());
        		reviews.put(c.getCourseId(), listreviews);
      		
        		courseMap.put(c.getCourseId(), c);
        	}
        	
        	// Retrieve Rating for professor
        	rating = ratingDao.getAvgRatingByProfessorId(professorId);
        	
        	//get session user
    		HttpSession session=req.getSession(false);  
    		if(session!=null){  
    			review.model.Login user = (review.model.Login) session.getAttribute("user");
    			req.setAttribute("user", user);
    		}
    			
        	req.setAttribute("rating", rating);
        	req.setAttribute("hash", hash);
        	req.setAttribute("star", "star");
        	req.setAttribute("professor", professor);
        	req.setAttribute("courses", courses);
        	req.setAttribute("reviews", reviews);
        	req.setAttribute("courseMap", courseMap);
        	req.setAttribute("session", session);
        	req.getRequestDispatcher("/ProfessorReview.jsp").forward(req, resp);
        	
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        } 
	}

}
