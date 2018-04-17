package review.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import review.dao.ReviewsDao;

@WebServlet("/likereview")
public class LikeReview extends HttpServlet {
protected ReviewsDao reviewDao;
	
	@Override
	public void init() throws ServletException {
		reviewDao = ReviewsDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int reviewId = Integer.parseInt(req.getParameter("ReviewId"));
        //update Review
        	try {
            		reviewDao.updateLikeCount(reviewId);
            
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
      
	}
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int reviewId = Integer.parseInt(req.getParameter("ReviewId"));
        
	}
}