package models;

import java.sql.Date;

public class Emprunt {

	   private int id;
	    private int id_exemplaire;
	    private int id_user;
	    private Date date_Emprunt;
	    private Date date_retour_Prevue;
	    private Date dateRetourReelle;
        private exemplaire exemplaire;
        private User user;
        public Emprunt() {
	    }
	 
	    public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId_exemplaire() {
			return id_exemplaire;
		}

		public void setId_exemplaire(int id_exemplaire) {
			this.id_exemplaire = id_exemplaire;
		}

		public int getId_user() {
			return id_user;
		}

		public void setId_user(int id_user) {
			this.id_user = id_user;
		}

		public Date getDate_Emprunt() {
			return date_Emprunt;
		}

		public void setDate_Emprunt(Date date_Emprunt) {
			this.date_Emprunt = date_Emprunt;
		}

		public Date getDate_retour_Prevue() {
			return date_retour_Prevue;
		}

		public void setDate_retour_Prevue(Date date_retour_Prevue) {
			this.date_retour_Prevue = date_retour_Prevue;
		}

		public Date getDateRetourReelle() {
			return dateRetourReelle;
		}

		public void setDateRetourReelle(Date dateRetourReelle) {
			this.dateRetourReelle = dateRetourReelle;
		}

		public exemplaire getExemplaire() {
			return exemplaire;
		}

		public void setExemplaire(exemplaire exemplaire) {
			this.exemplaire = exemplaire;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		
	    
	    
	  

	
	    
	    
	    
	    
	    
	
}
