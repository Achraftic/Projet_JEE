package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import util.Utilitaire;

public class ServiceUser {

    public boolean registerUser(String nom, String prenom, String email, String password) {

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
        	Utilitaire utilitaire = new Utilitaire();
            connection = utilitaire.getConnection();
            String query = "INSERT INTO user (`nom`, `prenom`, `email`, `password`, `role`) VALUES (?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, prenom);
            statement.setString(3, email);
            statement.setString(4, password);
            statement.setString(5, "user");

            // Execute the query
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    // user login
    public User loginUser(String email, String password) {
    	User user = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
        	Utilitaire utilitaire = new Utilitaire();
            connection = utilitaire.getConnection();
            String query = "SELECT * FROM user WHERE email= ? and password= ?";
            statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
       
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
            	user = new User();
            	
                int userId = resultSet.getInt("id");
                String userEmail = resultSet.getString("email");
                String userNom = resultSet.getString("nom");
                String userPrenom = resultSet.getString("prenom");
                String userRole=resultSet.getString("role");
                user.setId(userId);
                user.setEmail(userEmail);
                user.setNom(userNom);
                user.setPrenom(userPrenom);
                user.setRole(userRole);
    
               
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
		return user;
    

    
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
