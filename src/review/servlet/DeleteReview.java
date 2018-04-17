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
@WebServlet("/deletereview")
public class DeleteReview extends HttpServlet {
protected ReviewsDao reviewsDao;
	
	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int rid = Integer.parseInt(req.getParameter("rid"));
        int pid = Integer.parseInt(req.getParameter("pid"));
        //delete Review
        	try {
            		reviewsDao.delete(rid);
            		 messages.put("success", "Review Deleted");
            		
            
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
//        	req.getRequestDispatcher("/professorreview?id="+pid+"&deleted=true").forward(req, resp);
      	resp.sendRedirect(req.getContextPath()+"/professorreview?id="+pid+"&deleted=true");
      
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int rid = Integer.parseInt(req.getParameter("rid"));
        int pid = Integer.parseInt(req.getParameter("pid"));
        //delete Review
        	try {
            		reviewsDao.delete(rid);
            		 messages.put("success", "Review Deleted");
            		
            
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
//        	req.getRequestDispatcher("/professorreview?id="+pid+"&deleted=true").forward(req, resp);
     resp.sendRedirect(req.getContextPath()+"/professorreview?id="+pid+"&deleted=true");
  
}
}
