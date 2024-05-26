package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import metier.ServiceUser;
import models.User;

import java.io.IOException;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Your existing login logic
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");

	    ServiceUser userService = new ServiceUser();
	    User user = userService.loginUser(email, password);

	    if (user != null) {
	        // Login successful
	        HttpSession session = request.getSession(true);
	        session.setAttribute("user", user); // Store user information in the session
	        
	        // Set session timeout to 24 hours
	        session.setMaxInactiveInterval(24 * 60 * 60); // 24 hours

	        if ("admin".equals(user.getRole())) {
	            response.sendRedirect("/projet/ServletLivres");
	        } else {
	            response.sendRedirect("livres");
	        }
	    } else {
	        // Login failed
	        // Clear session attributes
	        request.getSession().invalidate();

	        request.setAttribute("errorMessage", "Invalid email or password");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
	        dispatcher.forward(request, response);
	    }
	}


}
