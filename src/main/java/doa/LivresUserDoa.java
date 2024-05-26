package doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Livre;
import util.Utilitaire;

public class LivresUserDoa {

	
	public List<Livre> getLivres() {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    List<Livre> listLivre = new ArrayList<>();  // Initialisation de la liste

	    try {
	        Utilitaire utilitaire = new Utilitaire();
	        connection = utilitaire.getConnection();
	        String query = "SELECT * FROM livre";
	        statement = connection.prepareStatement(query);
	        resultSet = statement.executeQuery();

	        while (resultSet.next()) {
	            Livre livre = new Livre();
	            livre.setId(resultSet.getInt("id"));
	            livre.setISBN(resultSet.getString("ISBN"));
	            livre.setTitre(resultSet.getString("titre"));
	            livre.setDescription(resultSet.getString("description"));
	            livre.setNbExemplaire(resultSet.getInt("nb_exemplaire"));
	            livre.setImage(resultSet.getString("image"));
	            livre.setGenre(resultSet.getString("genre"));
	            livre.setAuteur(resultSet.getString("auteur"));


	            listLivre.add(livre);
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

	    return listLivre;  // La liste est retournée, elle ne sera jamais null
	}
	
	
	 public String demandeLivre(int id_user, int id_exemplaire, String dateDemandeStr, String statut, String dateRetourPrevueStr) {
	        String message = "";
	        Connection connection = null;
	        PreparedStatement statement = null;
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	       ResultSet resultSet=null;
	        
	        try {
	            // Conversion des chaînes en Date
	            Date dateDemande = dateFormat.parse(dateDemandeStr);
	            Date dateRetourPrevue = dateFormat.parse(dateRetourPrevueStr);
	            
	            Utilitaire utilitaire = new Utilitaire();
	            connection = utilitaire.getConnection();
	            
	            // Vérifier si une demande en cours existe déjà pour cet utilisateur
	            String queryCheck = "SELECT COUNT(*) AS count FROM demande WHERE id_user = ? AND statut = 'en attente'";
	            statement = connection.prepareStatement(queryCheck);
	            statement.setInt(1, id_user);
	            resultSet = statement.executeQuery();

	            if (resultSet.next() && resultSet.getInt("count") > 0) {
	                message = "L'utilisateur a déjà une demande en cours.";
	            } else {
	                // Procéder à l'insertion de la nouvelle demande
	                String query = "INSERT INTO demande (id_user, id_exemplaire, date_demande, statut, date_retour_prevue) VALUES (?, ?, ?, ?, ?)";
	                statement = connection.prepareStatement(query);
	                statement.setInt(1, id_user);
	                statement.setInt(2, id_exemplaire);
	                statement.setDate(3, new java.sql.Date(dateDemande.getTime()));
	                statement.setString(4, statut);
	                statement.setDate(5, new java.sql.Date(dateRetourPrevue.getTime()));

	                int rowsInserted = statement.executeUpdate();
	                if (rowsInserted > 0) {
	                    message = "La demande a été enregistrée avec succès";
	                } else {
	                    message = "Il y a eu un problème lors de l'enregistrement de la demande";
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            message = "Erreur SQL : " + e.getMessage();
	        } catch (ParseException e) {
	            e.printStackTrace();
	            message = "Erreur de format de date : " + e.getMessage();
	        } finally {
	            try {
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return message;
	    }
	 
	 
	 public int  getExemplaireDisponnible(String ISBN ) {
		  Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet  resultSet= null;
	        int exemplaireId=0;
	        try {
	        	Utilitaire utilitaire = new Utilitaire();
		        connection = utilitaire.getConnection();
		        String query = "SELECT * FROM `exemplaire` where status='disponible' and ISBN=? LIMIT 1";
		        
		        statement = connection.prepareStatement(query);
		        statement.setString(1,ISBN);
		         resultSet = statement.executeQuery();
		        
		        if (resultSet.next()) {
	                exemplaireId = resultSet.getInt("id");
	            }
		       
		       
		       
	        }
	        catch (SQLException e) {
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
	        
	        return exemplaireId;

	 }

	 
	

}
