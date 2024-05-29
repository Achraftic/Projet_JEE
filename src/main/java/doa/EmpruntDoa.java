package doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Emprunt;
import models.Livre;
import models.User;
import models.exemplaire;
import util.Utilitaire;

public class EmpruntDoa {


	public List<Emprunt> getEmprunt() {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    List <Emprunt> Emprunts=new ArrayList<>(); 

	    try {
	        Utilitaire utilitaire = new Utilitaire();
	        connection = utilitaire.getConnection();
	        String query = "SELECT emprunt.*, exemplaire.id,exemplaire.ISBN,user.nom,user.prenom, livre.titre "+ "FROM emprunt "
	        		+ "INNER JOIN exemplaire ON emprunt.id_exemplaire = exemplaire.id "
	        		+ "INNER JOIN user ON emprunt.id_user = user.id "
	        		+ "INNER JOIN livre ON exemplaire.ISBN = livre.ISBN where Date_Retour_Réelle is null	";
	        
	        
	        statement = connection.prepareStatement(query);
	    

	        resultSet = statement.executeQuery();

	        while(resultSet.next()) {
	        	
	        	Emprunt emprunt= new Emprunt();
	        	User user =new User();
	        	exemplaire Exemplaire= new exemplaire();
	        	Livre livre =new Livre();
	        	
	        	emprunt.setId(resultSet.getInt("emprunt.id"));
	        	emprunt.setId_exemplaire(resultSet.getInt("id_exemplaire"));
	        
	            emprunt.setId_user(resultSet.getInt("id_user"));
	            emprunt.setDate_Emprunt(resultSet.getDate("date_Emprunt"));
	            emprunt.setDate_retour_Prevue(resultSet.getDate("date_detour_Prevue"));
	            emprunt.setDateRetourReelle(resultSet.getDate("Date_Retour_Réelle"));
	            
	            user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
	            
                livre.setTitre(resultSet.getString("titre"));
                // set other attributes of livre as needed
                
            	
	        	Exemplaire.setLivre(livre);
	        	Exemplaire.setISBN(resultSet.getString("exemplaire.ISBN"));
	        	
                emprunt.setExemplaire(Exemplaire);
                emprunt.setUser(user);
	        
	           
                Emprunts.add(emprunt);
	            
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return Emprunts;
	}
	

	
}
