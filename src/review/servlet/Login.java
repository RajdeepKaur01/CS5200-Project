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

import review.dao.LoginDao;

@WebServlet("/login")
public class Login extends HttpServlet {
protected LoginDao loginDao;
	
	@Override
	public void init() throws ServletException {
		loginDao = LoginDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        
    }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        review.model.Login user;
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        if (userName == null || userName.trim().isEmpty()) {
        	messages.remove("success");
            messages.put("error", "Please enter a valid UserName.");
            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        } 
        else if (password == null || password.trim().isEmpty()) {
        	 messages.remove("success");
        	 messages.put("error", "Please enter a valid password");
        	 req.getRequestDispatcher("/Login.jsp").forward(req, resp);
        }
        else {
        	// Retrieve User, and store as a message.
        	try {
            	user = loginDao.getLoginDetails(userName);
            	if(user==null || !user.getPassword().equals(password)) {
            		messages.remove("success");
            		messages.put("error", "Invalid UserName/Password");
            		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
            		
            	}
            	else{
            		messages.remove("error");
            		messages.put("success", "Welcome Back!!");
            		req.setAttribute("user", user);
            		HttpSession session=req.getSession();  
            	    session.setAttribute("user",user); 
        //    	    req.getRequestDispatcher("/search").forward(req, resp);
            	    resp.sendRedirect(req.getContextPath()+"/search");
            	}
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        }
       
      
        
    }
}
