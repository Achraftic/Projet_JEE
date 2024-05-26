package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Demande;
import models.Emprunt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import doa.EmpruntDoa;
import doa.demandeDoa;


/**
 * Servlet implementation class ServletDemande
 */
@WebServlet(
	    name = "ServletEmprunt",
	    urlPatterns = {"/emprunts"}
	)
public class ServletEmprunt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	  private EmpruntDoa doa ;
	  
	  public void init() {
	         doa = new EmpruntDoa() ;
	       
	    }
	
    public ServletEmprunt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String servletPath = request.getServletPath();
	
		 // HttpSession session = request.getSession(false);

 	    
    	 //  if (session == null || session.getAttribute("user") == null || !((User) session.getAttribute("user")).getRole().equals("admin")) {
    	 //      response.sendRedirect("login");
    	        
    	 //    return;
    	 // }
    	 // else {
    	 //System.out.println();
    	    	//  }
 	   
        switch (servletPath) {
            case "/emprunts":
            	getEmprunts(request, response);
           	 break;
         
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	 
	public void  getEmprunts (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  List<Emprunt> emprunts = doa.getEmprunt();
		 
	        request.setAttribute("emprunts", emprunts);
	        request.setAttribute("activePage", "emprunts");
	        request.getRequestDispatcher("./admin/emprunts.jsp").forward(request, response);
	     
	}
	

	
}
