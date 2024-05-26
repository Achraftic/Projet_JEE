package web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Livre;
import models.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import doa.LivresDoa;
import doa.LivresUserDoa;

/**
 * Servlet implementation class ServletUserInterface
 */
@WebServlet(
	    name = "ServletUserInterface",
	    urlPatterns = {"/livres", "/livre", "/demander", 
	    		"/ServletEtudiantAjouter", "/ServleLivreSupprimer","/ServletDetailLivre"}
	)
public class ServletUserInterface extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	  private LivresUserDoa doa ;
	  private LivresDoa doa2;
	  public void init() {
	         doa = new LivresUserDoa();
	         doa2 =new LivresDoa();
	    }
    public ServletUserInterface() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String servletPath = request.getServletPath();
	
  	   HttpSession session = request.getSession(false);

  	    
  	    if (session == null || session.getAttribute("user") == null ) {
  	        response.sendRedirect("login");
  	        
  	        return;
  	    }
  	   
         switch (servletPath) {
             case "/livres":
            	 getLivres(request, response);
                 break;
             case "/livre":
            	 detailLivre(request, response);
            	 break;
             case "/demander":
            	 demanderUnExemplaire(request, response);
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
	
	
	
	   private void getLivres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        List<Livre> listLivre = doa.getLivres();
	        request.setAttribute("livres", listLivre);
	        
	        request.getRequestDispatcher("./user/livres.jsp").forward(request, response);
	    }
	   
	   private void detailLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
		   int id =Integer.parseInt(request.getParameter("id"));
		   Livre livre=doa2.getUnLivres(id);
		   livre.setDisponible(doa2.LivreEstDisponible(livre.getISBN())); 
		   request.setAttribute("livre", livre);
		   request.getRequestDispatcher("./user/detailLivre.jsp").forward(request, response);
		   
		   
	   }
	   
	   private void demanderUnExemplaire(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    
		    int id_user = Integer.parseInt(request.getParameter("id_user"));
            int id_exemplaire = doa.getExemplaireDisponnible(request.getParameter("ISBN")) ;
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String dateDemandeStr = dateFormat.format(new Date());
            String statut = "en attente";
            String dateRetourPrevueStr = request.getParameter("date_retour_prevue");
            
            
           String  message=doa.demandeLivre(id_user, id_exemplaire, dateDemandeStr, statut, dateRetourPrevueStr);
            
           request.setAttribute("message", message);
		   response.sendRedirect("livres");
		   
	   }
	  

}
