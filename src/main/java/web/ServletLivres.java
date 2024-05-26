package web;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import models.Livre;
import util.Utilitaire;
import models.User;
import models.exemplaire;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import doa.ExemplairesDoa;
import doa.LivresDoa;

/**
 * Servlet implementation class ServletLivres
 */
@WebServlet(
    name = "ServletLivres",
    urlPatterns = {"/ServletAjouterLivre", "/ServletLivres", "/ServletLivreModifier", 
    		"/ServletEtudiantAjouter", "/ServleLivreSupprimer","/ServletDetailLivre"}
)
@MultipartConfig(
	    fileSizeThreshold   = 1024 * 1024 * 2,  // 2MB
	    maxFileSize         = 1024 * 1024 * 10, // 10MB
	    maxRequestSize      = 1024 * 1024 * 50  // 50MB
	)
public class ServletLivres extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private LivresDoa dao;
    private ExemplairesDoa dao1;

    @Override
    public void init() {
        dao = new LivresDoa();
        dao1 =new ExemplairesDoa();
    }

    public ServletLivres() {
        super();
    }

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
             case "/ServletAjouterLivre":
            	 request.setAttribute("activePage", "livres");
            	 request.getRequestDispatcher("./admin/ajouterLivre.jsp").forward(request, response);
                 break;
             case "/ServletLivres":
            	 getLivres(request, response);
            	 break;
             case "/ServletLivreModifier":
            	 editLivre(request, response);
            	 break;
             case "/ServletDetailLivre":
             detailLivre(request,response);
             break;

             default:
                 response.sendError(HttpServletResponse.SC_NOT_FOUND);
         }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  String servletPath = request.getServletPath();
          switch (servletPath) {
              case "/ServletAjouterLivre":
            	  ajouterLivre(request, response);
                  break;
              case "/ServleLivreSupprimer":
                 supprimerLivre(request, response);
                 break;
              case "/ServletLivreModifier":
               UpdateLivre(request, response);
               break;
              case "/AjouterExemplaire":
            	  AjouterExemplaire(request, response);
               break;
              default:
                  response.sendError(HttpServletResponse.SC_NOT_FOUND);
          }
    }

    private void getLivres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the list of books from the DAO
        List<Livre> listLivre = dao.getLivres();

        // Check the availability for each book
        for (Livre livre : listLivre) {
            boolean isDisponible = dao.LivreEstDisponible(livre.getISBN());
            livre.setDisponible(isDisponible); // Assuming Livre class has setDisponible method
        }

        // Set the updated list of books as a request attribute
        request.setAttribute("livres", listLivre);
        request.setAttribute("activePage", "livres");
        // Forward the request to the JSP page
        request.getRequestDispatcher("./admin/livres.jsp").forward(request, response);
    }

    
    private void ajouterLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String isbn = request.getParameter("ISBN");
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        String auteur = request.getParameter("auteur");
        String genre = request.getParameter("genre");
        String nb_exemplaire = request.getParameter("nb_exemplaire");
        
        Part filePart = request.getPart("file");
        request.setAttribute("activePage", "livres");
        String fileName = "";
        if (filePart != null) {
            fileName = getFileName(filePart);
        }

        // Save the file on the server
        String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        if (!fileName.equals("")) {
            filePart.write(uploadPath + File.separator + fileName);
        }

        // Add the book to the database
        boolean livreAjoute = dao.AjouterLivre(isbn, titre, description, fileName, Integer.parseInt(nb_exemplaire), genre, auteur);

        // Use a boolean flag to determine the next step
        if (livreAjoute) {
        	
            response.sendRedirect("ServletLivres");
        } else {
            request.setAttribute("errorMessage", "Failed to add the book. Please try again.");
            response.sendRedirect("ServletAjouterLivre");
            
        }
    }


    // Function to extract the file name from the part header
    
    private void UpdateLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String ISBN = request.getParameter("ISBN");
        String titre = request.getParameter("titre");
        String description = request.getParameter("description");
        String auteur = request.getParameter("auteur");
        String genre = request.getParameter("genre");
        String existingImageName = request.getParameter("image"); // Get the existing image name from hidden input

        Part filePart = request.getPart("file");
        String fileName = existingImageName; // Default to the existing image name

        if (filePart != null && filePart.getSize() > 0) { // Check if a new file is uploaded
            fileName = getFileName(filePart);

            // Save the file on the server
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            filePart.write(uploadPath + File.separator + fileName);
        }

        boolean isUpdated = dao.UpdateLivre(id, ISBN, titre, description, fileName, genre, auteur);

        if (isUpdated) {
            response.sendRedirect("ServletLivres");
        } else {
            request.setAttribute("message", "Failed to update the livre.");
            
            response.sendRedirect("ServletLivreModifier");
        }
    }

    // Helper method to get the file name from the part
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    
    
    private void editLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id =Integer.parseInt(request.getParameter("id"));
    	Livre livre=null;
         livre=dao.getUnLivres(id);
        request.setAttribute("livre", livre);
        request.setAttribute("activePage", "livres");
        request.getRequestDispatcher("./admin/editLivre.jsp").forward(request, response);
    }
  
    
    private void supprimerLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	int id =Integer.parseInt(request.getParameter("id"));
        dao.SupprimerLivre(id);
        response.sendRedirect("ServletLivres");
        
    }
    
    
    private void detailLivre(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	int id =Integer.parseInt(request.getParameter("id"));
    	Livre livre=null;
        livre=dao.getUnLivres(id);
    	String ISBN =livre.getISBN();
    	List<exemplaire> exemplaires=dao1.getExemplaires(ISBN);
        request.setAttribute("livre", livre);
        request.setAttribute("activePage", "livres");
        request.setAttribute("exemplaires", exemplaires);
        request.getRequestDispatcher("./admin/detailLivre.jsp").forward(request, response);

    	
    }
   
    
    private void  AjouterExemplaire (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String ISBN =request.getParameter("ISBN");
    	String message=dao1.AjouterExemplaire(ISBN);
    
    	request.setAttribute("message", message);
    	response.sendRedirect("ServletLivres");
    	 
    	
    	
    }
    
    
    
}