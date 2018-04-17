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

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import review.dao.CoursesDao;
import review.dao.LoginDao;
import review.dao.ProfessorCourseDataDao;
import review.dao.ProfessorDao;
import review.dao.ReviewsDao;
import review.dao.StudentsDao;
import review.dao.UniversityDao;
import review.model.Courses;
import review.model.Professor;
import review.model.Reviews;
import review.model.Reviews.CourseWork;
import review.model.Reviews.Difficulty;
import review.model.Students;
import review.model.University;

@WebServlet("/postreview")
public class PostReview extends HttpServlet{
	protected ReviewsDao reviewDao;
	protected ProfessorDao professorDao;
	protected ProfessorCourseDataDao pcDao;
	protected UniversityDao univDao;

	@Override
	public void init() throws ServletException {
		reviewDao = ReviewsDao.getInstance();
		professorDao = ProfessorDao.getInstance();
		pcDao = ProfessorCourseDataDao.getInstance();
		univDao = UniversityDao.getInstance();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		Professor prof = null;
		req.setAttribute("messages", messages);
		int professorId= Integer.parseInt(req.getParameter("id"));
		List<Courses> courses = new ArrayList<>();
		List<University> university = new ArrayList<>();
		
		//get session user
		HttpSession session=req.getSession(false);  
        if(session!=null){  
        review.model.Login user = (review.model.Login) session.getAttribute("user");
        req.setAttribute("user", user);
        }
        
       
        try {
        	 //get Professor
			prof = professorDao.getProfessorById(professorId);
			
			//Retrieve Courses 
			if(req.getParameter("univId")!=null){
				courses = CoursesDao.getInstance().getCourseByUniversityId(Integer.parseInt(req.getParameter("univId")));
			}
			
       	// Retrieve all University
        	university = univDao.getAllUniversities();
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        req.setAttribute("id", professorId);
        req.setAttribute("university", university);
        req.setAttribute("professor", prof);
        req.setAttribute("courses", courses);
        req.setAttribute("univId", req.getParameter("univId"));
		req.getRequestDispatcher("/PostReview.jsp").forward(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("IN POST");
		// Map for storing messages.
		Map<String, String> messages = new HashMap<String, String>();
		req.setAttribute("messages", messages);
		int professorId= Integer.parseInt(req.getParameter("id"));
		List<Courses> courses = new ArrayList<>();
		review.model.Login user=null;
		
		//get session user
		HttpSession session=req.getSession(false);  
        if(session!=null){  
        user = (review.model.Login) session.getAttribute("user");
        req.setAttribute("user", user);
        }
      
        Professor prof = null;
        try {
       	 //get Professor
        	prof = professorDao.getProfessorById(professorId);
        	
        	if(req.getParameter("univId")!=null){
				courses = CoursesDao.getInstance().getCourseByUniversityId(Integer.parseInt(req.getParameter("univId")));
			}
        	
		} catch (SQLException e) {
			e.printStackTrace();
		}
        req.setAttribute("univId", req.getParameter("univId"));
        req.setAttribute("id",professorId);
        req.setAttribute("professor", prof);
        req.setAttribute("courses", courses);
       
		try{
			Students student = StudentsDao.getInstance().getStudentyByUsername(user==null?"":user.getUserName());
			String coursename = req.getParameter("coursename");
			String university = req.getParameter("university");
			String description = req.getParameter("description");
			String year = req.getParameter("yearattended");
			boolean showname = req.getParameter("showname") == null;
			// Retrieve all University
        	req.setAttribute("university", univDao.getAllUniversities());
        	System.out.println("FOUND"+req.getParameter("coursemodal"));
        if(req.getParameter("coursemodal")!=null){
        	if(req.getParameter("coursemodal").equals("true")) messages.put("error", "Course Not added!! Try Again");
        	else messages.put("success", "Course added successfully!!");
        	req.getRequestDispatcher("/PostReview.jsp").forward(req, resp);
        }
        else if (university==null || coursename==null || university.equals("0") || coursename.equals("0")) {
				messages.remove("success");
				messages.put("error", "Please select or add new course");
				req.getRequestDispatcher("/PostReview.jsp").forward(req, resp);
		} 
		else if (description == null || description.trim().isEmpty()) {
			messages.remove("success");
			messages.put("error", "Please enter Review Description");
			req.getRequestDispatcher("/PostReview.jsp").forward(req, resp);
		} 
		
		else if (year == null || year.trim().isEmpty()) {
			messages.remove("success");
			messages.put("error", "Please enter year attended");
			req.getRequestDispatcher("/PostReview.jsp").forward(req, resp);
		}
		else {
			// Insert Review
				Courses course = CoursesDao.getInstance().getCourseById(Integer.parseInt(req.getParameter("coursename")));
				if(!pcDao.checkCourseProfessorJoin(professorId, course.getCourseId()))
					pcDao.createPCJoin(professorId,course.getCourseId());
				ReviewsDao.getInstance().create(new Reviews(student, prof, course, description, Difficulty.valueOf(req.getParameter("difficulty")), CourseWork.valueOf(req.getParameter("coursework")), Integer.parseInt(year), showname, 0, 0));
				messages.put("success", "Review created successfully");
				req.getRequestDispatcher("/professorreview?id="+professorId).forward(req, resp);

		}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
		

	}
}
