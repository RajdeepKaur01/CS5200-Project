package review.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class Logout extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)  
			throws ServletException, IOException {  
		
		HttpSession session=req.getSession();  
		session.invalidate();  
		 Map<String, String> messages = new HashMap<String, String>();
		 messages.put("success", "You are successfully logged out!!");
	     req.setAttribute("messages", messages);
//	     req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	     resp.sendRedirect(req.getContextPath()+"/Login.jsp");
}
}
