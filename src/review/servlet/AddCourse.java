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
import javax.servlet.http.HttpSession;

import review.dao.CoursesDao;

@WebServlet("/addcourse")
public class AddCourse extends HttpServlet {
protected CoursesDao coursesDao;
	
	@Override
	public void init() throws ServletException {
		coursesDao = coursesDao.getInstance();
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        String mcoursecode = req.getParameter("mcoursecode");
        String mcoursename = req.getParameter("mcoursename");
        String mdepartment = req.getParameter("mdepartment");
        String muniversity = req.getParameter("muniversity");
		
        if (mcoursecode == null || mcoursecode.trim().isEmpty() || mcoursename == null || mcoursename.trim().isEmpty() ||
        		mdepartment == null || mdepartment.trim().isEmpty() || muniversity == null) {
        	messages.remove("success");
            messages.put("error", "Course Not added!! Try again");
            req.getRequestDispatcher("/postreview?id="+req.getParameter("mprofessorid")+"&coursemodal=true").forward(req, resp);
        } 
        else {
        	// Add Course
        	try {
            	coursesDao.createNewCourse(mcoursecode, mcoursename, mdepartment, Integer.parseInt(muniversity));
            	messages.remove("error");
                messages.put("success", "Course added successfully!!");
                req.getRequestDispatcher("/postreview?id="+req.getParameter("mprofessorid")+"&coursemodal=false").forward(req, resp);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
       
      
        
    }
}
