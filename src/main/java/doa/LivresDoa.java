package doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Livre;
import util.Utilitaire;

public class LivresDoa {

	public boolean AjouterLivre(String ISBN, String titre, String description, String image, int nb_exemplaire, String genre, String auteur) {

	    Connection connection = null;
	    PreparedStatement statementLivre = null;
	    PreparedStatement statementExemplaire = null;

	    try {
	        Utilitaire utilitaire = new Utilitaire();
	        connection = utilitaire.getConnection();

	        // Begin transaction
	        connection.setAutoCommit(false);

	        // Insert into the livre table
	        String queryLivre = "INSERT INTO livre (`ISBN`, `titre`, `description`, `nb_exemplaire`, `image`, `genre`, `auteur`) VALUES (?,?,?,?,?,?,?)";
	        statementLivre = connection.prepareStatement(queryLivre);
	        statementLivre.setString(1, ISBN);
	        statementLivre.setString(2, titre);
	        statementLivre.setString(3, description);
	        statementLivre.setInt(4, nb_exemplaire);
	        statementLivre.setString(5, image);
	        statementLivre.setString(6, genre);
	        statementLivre.setString(7, auteur);

	        int rowsInsertedLivre = statementLivre.executeUpdate();

	        if (rowsInsertedLivre > 0) {
	            // Insert into the exemplaire table
	            String queryExemplaire = "INSERT INTO exemplaire (`status`, `ISBN`) VALUES (?,?)";
	            statementExemplaire = connection.prepareStatement(queryExemplaire);
	            for (int i = 0; i < nb_exemplaire; i++) {
	                statementExemplaire.setString(1, "disponible");
	                statementExemplaire.setString(2, ISBN);
	                statementExemplaire.addBatch();
	            }
	            statementExemplaire.executeBatch();

	            // Commit transaction
	            connection.commit();

	            return true;
	        } else {
	            connection.rollback();
	            return false;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        try {
	            if (connection != null) {
	                connection.rollback();
	            }
	        } catch (SQLException rollbackEx) {
	            rollbackEx.printStackTrace();
	        }
	        return false;
	    } finally {
	        try {
	            if (statementLivre != null) statementLivre.close();
	            if (statementExemplaire != null) statementExemplaire.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


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



public void SupprimerLivre(int id) {
	    Connection connection = null;
	    PreparedStatement statement = null;

	    try {
	        Utilitaire utilitaire = new Utilitaire();
	        connection = utilitaire.getConnection();
	        String query = "DELETE FROM livre WHERE id=?";
	        statement = connection.prepareStatement(query);  // Initialiser le PreparedStatement
	        statement.setInt(1, id);

	        // Exécuter la requête de suppression
	        int rowsDeleted = statement.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Un livre a été supprimé avec succès.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}

public boolean  UpdateLivre(int id, String ISBN, String titre, String description, String image, String genre, String auteur) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        Utilitaire utilitaire = new Utilitaire();
        connection = utilitaire.getConnection();

        String query = "UPDATE livre SET ISBN = ?, titre = ?, description = ?, image = ?, genre = ?, auteur = ? WHERE id = ?";
        statement = connection.prepareStatement(query);
        statement.setString(1, ISBN);
        statement.setString(2, titre);
        statement.setString(3, description);
        statement.setString(4, image);
        statement.setString(5, genre);
        statement.setString(6, auteur);
        statement.setInt(7, id);

        int rowsUpdated = statement.executeUpdate();
       return rowsUpdated>0;

    } catch (SQLException e) {
    	return false;
      
    } finally {
        try {
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

public Livre getUnLivres(int id ) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    Livre livre = new Livre();
     // Initialisation de la liste

    try {
        Utilitaire utilitaire = new Utilitaire();
        connection = utilitaire.getConnection();
        String query = "SELECT * FROM livre where id = ?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        resultSet = statement.executeQuery();

        if(resultSet.next()) {
            livre = new Livre();
            livre.setId(resultSet.getInt("id"));
            livre.setISBN(resultSet.getString("ISBN"));
            livre.setTitre(resultSet.getString("titre"));
            livre.setDescription(resultSet.getString("description"));
            livre.setNbExemplaire(resultSet.getInt("nb_exemplaire"));
            livre.setImage(resultSet.getString("image"));
            livre.setGenre(resultSet.getString("genre"));
            livre.setAuteur(resultSet.getString("auteur"));
            
           

          
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
	return livre;

     // La liste est retournée, elle ne sera jamais null
}


public boolean LivreEstDisponible(String ISBN) {
    Utilitaire utilitaire = new Utilitaire();
    boolean isAvailable = false;

    try (
        Connection connection = utilitaire.getConnection();
        PreparedStatement selectStatement = connection.prepareStatement("SELECT id FROM exemplaire WHERE status='disponible' AND ISBN=? LIMIT 1")
    ) {
        // Définir le paramètre ISBN dans la requête
        selectStatement.setString(1, ISBN);
        try (ResultSet resultSet = selectStatement.executeQuery()) {
            isAvailable = resultSet.next(); // Si un exemplaire disponible est trouvé, retourne true
        }

        // Préparer la requête de mise à jour
        try (PreparedStatement updateStatement = connection.prepareStatement("UPDATE livre SET disponible = ? WHERE ISBN = ?")) {
            updateStatement.setBoolean(1, isAvailable); // Met à jour disponible à true ou false
            updateStatement.setString(2, ISBN);
            updateStatement.executeUpdate();
        }
    } catch (SQLException e) {
        // Gérer l'exception de manière appropriée
        e.printStackTrace();
    }

    return isAvailable;
}



}

