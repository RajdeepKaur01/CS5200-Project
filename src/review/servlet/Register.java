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

import review.dao.LoginDao;
import review.dao.StudentsDao;
import review.dao.UniversityDao;
import review.model.Students;
import review.model.University;

@WebServlet("/register")
public class Register extends HttpServlet {
	protected StudentsDao studentDao;
	protected UniversityDao universityDao;

	@Override
	public void init() throws ServletException {
		studentDao = StudentsDao.getInstance();
		universityDao = UniversityDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		List<University> university = new ArrayList<>();
		try {
			 university = universityDao.getAllUniversities();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("university", university);
		req.setAttribute("messages", messages);
		req.getRequestDispatcher("/Register.jsp").forward(req, resp);

	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        review.model.Login user;
        
        List<University> university = new ArrayList<>();
		try {
			 university = universityDao.getAllUniversities();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		req.setAttribute("university", university);
        
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        int universityId = Integer.parseInt(req.getParameter("universityid"));
        String graduationyear =req.getParameter("graduationyear");
        
        if (userName == null || userName.trim().isEmpty()) {
            messages.put("error", "Please enter a valid UserName.");
            req.getRequestDispatcher("/Register.jsp").forward(req, resp);
        } 
        else if (password == null || password.trim().isEmpty()) {
        	 messages.put("error", "Please enter a valid password");
        	 req.getRequestDispatcher("/Register.jsp").forward(req, resp);
        }
        else if (firstname == null || firstname.trim().isEmpty()) {
       	 messages.put("error", "Please enter a valid first name");
       	 req.getRequestDispatcher("/Register.jsp").forward(req, resp);
       }
        else if (lastname == null || lastname.trim().isEmpty()) {
          	 messages.put("error", "Please enter a valid last name");
          	 req.getRequestDispatcher("/Register.jsp").forward(req, resp);
          }
        else if (graduationyear == null || graduationyear.trim().isEmpty()) {
          	 messages.put("error", "Please enter a valid graduation year");
          	 req.getRequestDispatcher("/Register.jsp").forward(req, resp);
          }
        else {
        	// Retrieve User, and store as a message.
        	try {
            	user = studentDao.getLoginDetails(userName);
            	if(user!=null) {
            		messages.remove("success");
            		messages.put("error", "Username is already taken!!");
            		 req.getRequestDispatcher("/Register.jsp").forward(req, resp);
            	}
            	else{
            		Students newUser = studentDao.create(new Students(userName,password,review.model.Login.Roles.valueOf("STUDENT"),firstname,lastname,universityDao.getUniversityById(universityId),Integer.parseInt(graduationyear)));
            		req.setAttribute("user", newUser);
            		messages.remove("error");
            		messages.put("success", "Registered Successfully!!");
                    req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            	}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
    }
}
