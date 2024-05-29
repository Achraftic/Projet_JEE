package doa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.User;
import util.Utilitaire;

public class UserDoa {

	public  List<User>  getUsers() {
		
		Connection connection=null;
		PreparedStatement statement=null;
		ResultSet resultset =null;
		  List<User> userList =new ArrayList<>();
		
		try {
			Utilitaire utilitaire = new Utilitaire();
			connection = utilitaire.getConnection();
			String query="select * from user where role = 'user'";
			statement=connection.prepareStatement(query);
			resultset = statement.executeQuery();
			while(resultset.next()) {
				User user=new  User();
				user.setNom(resultset.getString("nom"));
				user.setPrenom(resultset.getString("prenom"));
				user.setId(resultset.getInt("id"));
				user.setEmail(resultset.getString("email"));
				
				userList.add(user);
				
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
	            if (resultset != null) resultset.close();
	            if (statement != null) statement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		}
		return userList;
	}
	

}
