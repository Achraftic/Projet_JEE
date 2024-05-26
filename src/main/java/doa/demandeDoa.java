package doa;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import models.Demande;
import models.Livre;
import models.User;
import models.exemplaire;
import util.Utilitaire;

public class demandeDoa {

	
	


	    public List<Demande> getDemandes() {
	        List<Demande> demandes = new ArrayList<>();
	        String query = "SELECT demande.*, user.*, exemplaire.*, livre.* " +
	                "FROM demande " +
	                "INNER JOIN user ON demande.id_user = user.id " +
	                "INNER JOIN exemplaire ON demande.id_exemplaire = exemplaire.id " +
	                "INNER JOIN livre ON exemplaire.ISBN = livre.ISBN " +
	                "WHERE statut = 'en attente'";


	        try (Connection connection = new Utilitaire().getConnection();
	             PreparedStatement statement = connection.prepareStatement(query);
	             ResultSet resultSet = statement.executeQuery()) {

	            while (resultSet.next()) {
	                Demande demande = new Demande();
	                User user = new User();
	                exemplaire exemplaire = new exemplaire();
	                Livre livre = new Livre();

	                // Set user details
	                user.setId(resultSet.getInt("user.id"));
	                user.setNom(resultSet.getString("user.nom"));
	                user.setPrenom(resultSet.getString("user.prenom"));

	                // Set livre details
	                livre.setId(resultSet.getInt("livre.id"));
	                livre.setISBN(resultSet.getString("livre.ISBN"));
	                livre.setTitre(resultSet.getString("livre.titre"));

	                // Set exemplaire details
	                exemplaire.setId(resultSet.getInt("exemplaire.id"));
	                exemplaire.setStatus(resultSet.getString("exemplaire.status"));
	                exemplaire.setISBN(resultSet.getString("exemplaire.ISBN"));
	                exemplaire.setLivre(livre);

	                // Set demande details
	                demande.setId(resultSet.getInt("demande.id"));
	                demande.setUser(user);
	                demande.setExemplaire(exemplaire);
	                demande.setDate_demande(resultSet.getDate("demande.date_demande"));
	                demande.setStatut(resultSet.getString("demande.statut"));
	                demande.setDate_retour_prevue(resultSet.getDate("demande.date_retour_prevue"));

	                demandes.add(demande);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return demandes;
	    }
	    
	    public Demande getDemandeById(int demandeId) {
	        Connection connection = null;
	        PreparedStatement statement = null;
	        ResultSet resultSet = null;
	        Demande demande = null;

	        try {
	            Utilitaire utilitaire = new Utilitaire();
	            connection = utilitaire.getConnection();

	            String query = "SELECT * FROM demande WHERE id = ?";
	            statement = connection.prepareStatement(query);
	            statement.setInt(1, demandeId);
	            resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                demande = new Demande();
	                demande.setId(resultSet.getInt("id"));
	                demande.setId_user(resultSet.getInt("id_user"));
	                demande.setId_exemplaire(resultSet.getInt("id_exemplaire"));
	                demande.setDate_demande(resultSet.getDate("date_demande"));
	                demande.setStatut(resultSet.getString("statut"));
	                demande.setDate_retour_prevue(resultSet.getDate("date_retour_prevue"));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            // Handle SQL exceptions
	        } finally {
	            try {
	                if (resultSet != null) resultSet.close();
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException ex) {
	                ex.printStackTrace();
	            }
	        }

	        return demande;
	    }
	    
	    
	    
	    public void AccepterDemandeOrRefuse(Demande demande, boolean flag) throws SQLException {
	        Connection connection = null;
	        PreparedStatement statement = null;

	        try {
	            Utilitaire utilitaire = new Utilitaire();
	            connection = utilitaire.getConnection();

	            // Begin transaction
	            connection.setAutoCommit(false);

	            String updateQuery = "";
	            String insertQuery = "";

	          
	            if (flag) {
	                updateQuery = "UPDATE demande SET statut = 'accepter' WHERE id = ?";
	                insertQuery = "INSERT INTO emprunt (id_exemplaire, id_user, date_Emprunt, date_detour_Prevue) " +
	                              "VALUES (?, ?, ?, ?)";
	            } else {
	                updateQuery = "UPDATE demande SET statut = 'refuser' WHERE id = ?";
	                insertQuery = null;
	            }

	            statement = connection.prepareStatement(updateQuery);
	            statement.setInt(1, demande.getId());
	            int rowsUpdated = statement.executeUpdate();

	            if (rowsUpdated > 0 && insertQuery != null) {
	                // If the update was successful and insert query is provided, proceed with insertion
	                statement = connection.prepareStatement(insertQuery);
	                statement.setInt(1, demande.getId_exemplaire());
	                statement.setInt(2, demande.getId_user());
	                statement.setDate(3, new Date(Calendar.getInstance().getTimeInMillis()));
	                statement.setDate(4, demande.getDate_retour_prevue());
	                statement.executeUpdate();
	                connection.commit();
	            } 

	            // Commit transaction
	         

	        } catch (SQLException e) {
	            // Rollback transaction on SQL exception
	            if (connection != null) {
	                connection.rollback();
	            }
	            throw e; // Re-throw the exception to the caller for handling
	        } finally {
	            // Close resources and reset auto-commit in the finally block
	            try {
	                if (statement != null) {
	                    statement.close();
	                }
	                if (connection != null) {
	                    connection.setAutoCommit(true);
	                    connection.close();
	                }
	            } catch (SQLException ex) {
	                ex.printStackTrace(); // Handle resource closure exception
	            }
	        }
	    }

	
	 
	
	
}
