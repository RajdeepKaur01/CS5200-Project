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

import review.dao.ProfessorDao;

@WebServlet("/deleteprofessor")
public class DeleteProfessor extends HttpServlet {
protected ProfessorDao professorDao;
	
	@Override
	public void init() throws ServletException {
		professorDao = ProfessorDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        int id = Integer.parseInt(req.getParameter("id"));
        //delete Professor
        	try {
            		professorDao.delete(id);
            		 messages.put("success", "Professor Deleted");
            		
            
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
//        	req.getRequestDispatcher("/search?deleted=true").forward(req, resp);
        	resp.sendRedirect(req.getContextPath()+"/search?deleted=true");
      
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
      
        int id = Integer.parseInt(req.getParameter("id"));
        //delete Professor
    	try {
        		professorDao.delete(id);
        		 messages.put("success", "Professor Deleted !! ");
        		  req.setAttribute("messages", messages);
        
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
//    	req.getRequestDispatcher("/search?deleted=true").forward(req, resp);
    	resp.sendRedirect(req.getContextPath()+"/search?deleted=true");
  
}
}