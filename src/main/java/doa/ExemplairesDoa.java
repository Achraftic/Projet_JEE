package doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.exemplaire;
import util.Utilitaire;

public class ExemplairesDoa {

	public exemplaire getExemplairesLivre(int id, int idLivre) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    exemplaire exemplaire = null;

	    try {
	        Utilitaire utilitaire = new Utilitaire();
	        connection = utilitaire.getConnection();
	        String query = "SELECT exemplaire.* FROM exemplaire " + "INNER JOIN livre ON exemplaire.livre_id = livre.id " +
	                       "WHERE exemplaire.id = ? AND livre.id = ?";
	        statement = connection.prepareStatement(query);
	        statement.setInt(1, id);
	        statement.setInt(2, idLivre);
	        resultSet = statement.executeQuery();

	        if(resultSet.next()) {
	            exemplaire = new exemplaire();
	            exemplaire.setId(resultSet.getInt("id"));
	            exemplaire.setISBN(resultSet.getString("ISBN"));
	            exemplaire.setStatus(resultSet.getString("status"));
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
	    return exemplaire;
	}
	
	
	public List<exemplaire> getExemplaires( String ISBN) {
	    Connection connection = null;
	    PreparedStatement statement = null;
	    ResultSet resultSet = null;
	    List <exemplaire> Exemplaire=new ArrayList<>(); 

	    try {
	        Utilitaire utilitaire = new Utilitaire();
	        connection = utilitaire.getConnection();
	        String query = "SELECT * FROM exemplaire where ISBN =?";
	        statement = connection.prepareStatement(query);
	        statement.setString(1, ISBN);

	        resultSet = statement.executeQuery();

	        while(resultSet.next()) {
	        	
	        	exemplaire exemplaire1= new exemplaire();
	        	
	            exemplaire1.setId(resultSet.getInt("id"));
	            exemplaire1.setISBN(resultSet.getString("ISBN"));
	            exemplaire1.setStatus(resultSet.getString("status"));
	            Exemplaire.add(exemplaire1);
	            
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
	    return Exemplaire;
	}
	public String AjouterExemplaire(String ISBN) {
	    Connection connection = null;
	    PreparedStatement insertStatement = null;
	    PreparedStatement countStatement = null;
	    PreparedStatement updateStatement = null;
	    ResultSet resultSet = null;
	    String message = null;
	    
	    try {
	        Utilitaire utilitaire = new Utilitaire();
	        connection = utilitaire.getConnection();
	        
	        // Insert the new exemplaire
	        String insertQuery = "INSERT INTO exemplaire (status, ISBN) VALUES (?, ?)";
	        insertStatement = connection.prepareStatement(insertQuery);
	        insertStatement.setString(1, "disponible");
	        insertStatement.setString(2, ISBN);
	        
	        int rowsInserted = insertStatement.executeUpdate();
	        
	        if (rowsInserted > 0) {
	            // Get the count of exemplaires for the given ISBN
	            String countQuery = "SELECT COUNT(*) AS count FROM exemplaire WHERE ISBN = ?";
	            countStatement = connection.prepareStatement(countQuery);
	            countStatement.setString(1, ISBN);
	            resultSet = countStatement.executeQuery();
	            
	            if (resultSet.next()) {
	                int count = resultSet.getInt("count");
	                
	                // Update the nb_exemplaire field in the livre table
	                String updateQuery = "UPDATE livre SET nb_exemplaire = ? WHERE ISBN = ?";
	                updateStatement = connection.prepareStatement(updateQuery);
	                updateStatement.setInt(1, count);
	                updateStatement.setString(2, ISBN);
	                
	                int rowsUpdated = updateStatement.executeUpdate();
	                
	                if (rowsUpdated > 0) {
	                    message = "Exemplaire ajouté avec succès";
	                } else {
	                    message = "Problème lors de la mise à jour de nb_exemplaire";
	                }
	            }
	        } else {
	            message = "Problème lors de l'ajout de l'exemplaire";
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        message = "Erreur SQL : " + e.getMessage();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (insertStatement != null) insertStatement.close();
	            if (countStatement != null) countStatement.close();
	            if (updateStatement != null) updateStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return message;
	}

	
	 
	
	
	

}
