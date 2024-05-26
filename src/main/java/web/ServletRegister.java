package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import metier.ServiceUser;
/**
 * Servlet implementation class ServletRegister
 */
@WebServlet("/register")
public class ServletRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegister() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		request.getRequestDispatcher("register.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // Retrieve data from the registration form
	    String nom = request.getParameter("nom");
	    String prenom = request.getParameter("prenom");
	    String email = request.getParameter("email");
	    String password = request.getParameter("password");
	    String confirmPassword = request.getParameter("confirmpassword");
	    ServiceUser service = new ServiceUser();
	    String message = "";

	    // Validate the data
	    if (nom != null && !nom.isEmpty() && prenom != null && !prenom.isEmpty() &&
	        email != null && !email.isEmpty() && password != null && !password.isEmpty() &&
	        confirmPassword != null && !confirmPassword.isEmpty()) {
	       
	        if (password.equals(confirmPassword)) {
	            
	            if (service.registerUser(nom, prenom, email, password)) {
	                
	                response.sendRedirect("login.jsp");
	                return;
	            } else {
	                
	                message = "Probleme de registration ";
	            }
	        } else {
	           
	            message = "les mot de passe ne sont pas correspondant ";
	        }
	    } else {
	       
	        message = "tout les champs doit etre remplis";
	    }

	    // Set error message and redirect to the registration page
	    request.setAttribute("errorMessage", message);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
	    dispatcher.forward(request, response);
	}

}
