package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Demande;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import doa.demandeDoa;


/**
 * Servlet implementation class ServletDemande
 */
@WebServlet(
	    name = "ServletDemande",
	    urlPatterns = {"/demandes","/AccepterDemande","/RefuserDemande"}
	)
public class ServletDemande extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	  private demandeDoa doa ;
	  
	  public void init() {
	         doa = new demandeDoa() ;
	       
	    }
	
    public ServletDemande() {
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
            case "/demandes":
           	 getDemandes(request, response);
           	 
                break;
                
            case "/AccepterDemande":
			try {
				AccepterDemande(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              	 
                   break;
            case "/RefuserDemande":
			try {
				RefuserDemande(request, response);
			} catch (ServletException | IOException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
              	 
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

	 
	public void  getDemandes (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  List<Demande> demandes = doa.getDemandes() ;
	        request.setAttribute("demandes", demandes);
	        request.setAttribute("activePage", "demandes");
	        request.getRequestDispatcher("./admin/demandes.jsp").forward(request, response);
	     
	}
	
	public void AccepterDemande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  {
		
		int id =Integer.parseInt(request.getParameter("id"));
		
		Demande demande=doa.getDemandeById(id);
		
		doa.AccepterDemandeOrRefuse(demande, true);
		
		response.sendRedirect("demandes");
	}
	
	
public void RefuserDemande(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  {
		
		int id =Integer.parseInt(request.getParameter("id"));
		
		Demande demande=doa.getDemandeById(id);
		
		doa.AccepterDemandeOrRefuse(demande, false);
		
		response.sendRedirect("demandes");
	}
	
}
