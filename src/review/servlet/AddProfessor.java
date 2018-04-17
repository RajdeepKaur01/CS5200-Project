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

import review.dao.ProfessorDao;
import review.dao.UniversityDao;
import review.model.Professor;
import review.model.University;

@WebServlet("/addprofessor")
public class AddProfessor extends HttpServlet {
protected ProfessorDao professorDao;
protected UniversityDao univDao;
	
	@Override
	public void init() throws ServletException {
		professorDao=ProfessorDao.getInstance();
		univDao = UniversityDao.getInstance();
	}
	
	@Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        List<University> university = new ArrayList<>();
        try {
        	university = univDao.getAllUniversities();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      //get session user
      		HttpSession session=req.getSession(false);  
      		if(session!=null){  
      			review.model.Login user = (review.model.Login) session.getAttribute("user");
      			req.setAttribute("user", user);
      		}
        req.setAttribute("university", university);
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/AddProfessor.jsp").forward(req, resp);
        
    }
	
	@Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
  		throws ServletException, IOException {
		
		  // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        List<University> university = new ArrayList<>();
        try {
        	university = univDao.getAllUniversities();
        	// Add Professor to Database
        	String firstname = req.getParameter("firstname");
        	String lastname = req.getParameter("lastname");
        	int bachelors = Integer.parseInt(req.getParameter("bachelors"));
        	int masters = Integer.parseInt(req.getParameter("masters"));
        	int phd = Integer.parseInt(req.getParameter("phd"));
        	String teachinguniversity = req.getParameter("teachinguniversity");
        	int joiningyear = req.getParameter("joiningyear")==""?0:Integer.parseInt(req.getParameter("joiningyear"));
        	String rank=req.getParameter("rank");
        	String url=req.getParameter("url");
        	String photourl=req.getParameter("photourl");
        	if (firstname == null || firstname.trim().isEmpty()) {
            	messages.remove("success");
                messages.put("error", "Please enter a valid FirstName.");
                
            } 
        	else if (lastname == null || lastname.trim().isEmpty()) {
            	messages.remove("success");
                messages.put("error", "Please enter a valid LastName.");
              
            } 
        	else if (teachinguniversity.equals("0")) {
            	messages.remove("success");
                messages.put("error", "Please select Teaching University.");
               
            } 
        	else{
        		professorDao.create(new Professor(firstname, lastname, univDao.getUniversityById(bachelors), univDao.getUniversityById(masters), univDao.getUniversityById(phd), univDao.getUniversityById(Integer.parseInt(teachinguniversity)), joiningyear, rank, url, photourl));
        		messages.remove("error");
                messages.put("success", "Professor Added.");
        	}
        	} catch (SQLException e) {
			e.printStackTrace();
		}
      //get session user
      		HttpSession session=req.getSession(false);  
      		if(session!=null){  
      			review.model.Login user = (review.model.Login) session.getAttribute("user");
      			req.setAttribute("user", user);
      		}
        req.setAttribute("university", university);
        req.setAttribute("messages", messages);
        req.getRequestDispatcher("/AddProfessor.jsp").forward(req, resp);
	}
	
//	@Override
//    public void doPost(HttpServletRequest req, HttpServletResponse resp)
//    		throws ServletException, IOException {
//        // Map for storing messages.
//        Map<String, String> messages = new HashMap<String, String>();
//        req.setAttribute("messages", messages);
//        List<University> universities = new ArrayList<>();
//        try {
//			universities = univDao.getAllUniversities();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        req.setAttribute("universities", universities);
//        req.setAttribute("messages", messages);
//        
//        review.model.Login user;
//        String userName = req.getParameter("username");
//        String password = req.getParameter("password");
//        if (userName == null || userName.trim().isEmpty()) {
//        	messages.remove("success");
//            messages.put("error", "Please enter a valid UserName.");
//            req.getRequestDispatcher("/Login.jsp").forward(req, resp);
//        } 
//        else if (password == null || password.trim().isEmpty()) {
//        	 messages.remove("success");
//        	 messages.put("error", "Please enter a valid password");
//        	 req.getRequestDispatcher("/Login.jsp").forward(req, resp);
//        }
//        else {
//        	// Retrieve User, and store as a message.
//        	try {
//            	user = loginDao.getLoginDetails(userName);
//            	if(user==null || !user.getPassword().equals(password)) {
//            		messages.remove("success");
//            		messages.put("error", "Invalid UserName/Password");
//            		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
//            		
//            	}
//            	else{
//            		messages.remove("error");
//            		messages.put("success", "Welcome Back!!");
//            		req.setAttribute("user", user);
//            		HttpSession session=req.getSession();  
//            	    session.setAttribute("user",user); 
//        //    	    req.getRequestDispatcher("/search").forward(req, resp);
//            	    resp.sendRedirect(req.getContextPath()+"/search");
//            	}
//            } catch (SQLException e) {
//    			e.printStackTrace();
//    			throw new IOException(e);
//            }
//        }
//       
//      
//        
//    }
}

