package review.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
import review.dao.CoursesDao;
import review.dao.ProfessorCourseDataDao;
import review.dao.ProfessorDao;
import review.dao.ReviewsDao;
import review.dao.StudentsDao;
import review.model.Comments;
import review.model.Courses;
import review.model.Professor;
import review.model.Reviews;
import review.model.Students;
import review.model.Reviews.CourseWork;
import review.model.Reviews.Difficulty;

@WebServlet("/addcomment")
public class AddComment  extends HttpServlet{
	protected CommentsDao commentDao;

	@Override
	public void init() throws ServletException {
		commentDao=CommentsDao.getInstance();
	}

		@Override
		public void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			// Map for storing messages.
			Map<String, String> messages = new HashMap<String, String>();
			req.setAttribute("messages", messages);
			
			int reviewId= Integer.parseInt(req.getParameter("reviewid"));
			req.setAttribute("reviewId", reviewId);
			HttpSession session = req.getSession();
			
			review.model.Login user=null;
			if(session!=null){  
		        user = (review.model.Login) session.getAttribute("user");
		 
		        }
	       
	        try {
	        	Reviews rr= ReviewsDao.getInstance().getReviewById(reviewId);
	        	System.out.println(req.getParameter("showname"));
	        	commentDao.create(new Comments(StudentsDao.getInstance().getStudentyByUsername(user.getUserName()),
	        			rr, req.getParameter("addcomment"), !Boolean.parseBoolean(req.getParameter("showname"))));
	       System.out.println("Comment Posted");
	       List<Comments> comments = commentDao.getCommentsByReviewId(reviewId);       	
			} catch (SQLException e) {
				e.printStackTrace();
			}
	        


		}
	

@Override
public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
	
	// Map for storing messages.
	Map<String, String> messages = new HashMap<String, String>();
	req.setAttribute("messages", messages);
	
	int reviewId= Integer.parseInt(req.getParameter("reviewid"));

	HttpSession session = req.getSession();
	review.model.Login user=null;
	if(session!=null){  
        user = (review.model.Login) session.getAttribute("user");
    	//session.setAttribute("reviewId", reviewId);
       
        }
   
    try {
    	List<Comments> comments = commentDao.getCommentsByReviewId(reviewId);
    	req.setAttribute("comments", comments);
    	req.setAttribute("reviewId", reviewId);
    
	       
	} catch (SQLException e) {
		e.printStackTrace();
	}
    
req.getRequestDispatcher("/AddComment.jsp").forward(req, resp);

}
}

