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

import review.dao.LoginDao;
import review.dao.ProfessorDao;
import review.dao.UniversityDao;
import review.model.Professor;
import review.model.University;

@WebServlet("/search")
public class Search extends HttpServlet{
	protected ProfessorDao professorDao ;
	protected UniversityDao universityDao;

	@Override
	public void init() throws ServletException {
		professorDao = ProfessorDao.getInstance();
		universityDao = universityDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		req.setAttribute("deleted", req.getParameter("deleted"));
		//get session user
		HttpSession session=req.getSession(false);  
		if(session!=null){  
			review.model.Login user = (review.model.Login) session.getAttribute("user");
			req.setAttribute("user", user);
		}

		try {
			List<University> universities = universityDao.getAllUniversities();
			System.out.println("GET UNIVERSITY SIZE"+universities.size());
			req.setAttribute("universities", universities);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("HELLO GET");
		req.getRequestDispatcher("/Search.jsp").forward(req, resp);

	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		req.setAttribute("deleted", req.getParameter("deleted"));
		List<Professor> professors;
		String univ =  req.getParameter("univ");
		
		try {
			List<University> universities = universityDao.getAllUniversities();
			System.out.println(" POST UNIVERSITY SIZE"+universities.size());
			req.setAttribute("universities", universities);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		
		if(univ==null){
			
			String firstname = req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
	
			if ((firstname == null || firstname.trim().isEmpty()) && (lastname == null || lastname.trim().isEmpty())) {
				messages.remove("success");
				messages.put("error", "Please enter Professor details");
				req.getRequestDispatcher("/Search.jsp").forward(req, resp);
			} 
			else if (firstname == null || firstname.trim().isEmpty()) {
				//Search with lastname
				try {
					professors = professorDao.getProfessorsFromLastName(lastname);
					if(professors.size()==0) {
						messages.remove("success");
						messages.put("error", "No Such Professor Exists!!");
						req.getRequestDispatcher("/Search.jsp").forward(req, resp);
					}
					else{
						req.setAttribute("professors", professors);
						req.getRequestDispatcher("/Search.jsp").forward(req, resp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				} 
			}
			else if (lastname == null || lastname.trim().isEmpty()) {
				//Search with firstname
				try {
					professors = professorDao.getProfessorsFromFirstName(firstname);
					if(professors.size()==0) {
						messages.remove("success");
						messages.put("error", "No Such Professor Exists!!");
						req.getRequestDispatcher("/Search.jsp").forward(req, resp);
					}
					else{
						req.setAttribute("professors", professors);
						req.getRequestDispatcher("/Search.jsp").forward(req, resp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				} 
			}
			else {
				//Search with firstname and lastname
				try {
					professors = professorDao.getProfessorsFromtName(firstname,lastname);
					if(professors.size()==0) {
						messages.remove("success");
						messages.put("error", "No Such Professor Exists!!");
						req.getRequestDispatcher("/Search.jsp").forward(req, resp);
					}
					else{
						req.setAttribute("professors", professors);
						req.getRequestDispatcher("/Search.jsp").forward(req, resp);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				} 
	
			}
		}
		else{
			
			int universityName = Integer.parseInt(req.getParameter("universityname"));
			req.setAttribute("selectedId", universityName);
			try {
					professors = professorDao.getProfessorsFromUniversity(universityName);
					if(professors.size()==0) {
						messages.remove("success");
						messages.put("error", "No Professor registered for selected university!!");
						req.getRequestDispatcher("/Search.jsp").forward(req, resp);
					}
					else{
						req.setAttribute("professors", professors);
						req.getRequestDispatcher("/Search.jsp").forward(req, resp);
					}
					
				}catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				} 
			
		}
	}
}
