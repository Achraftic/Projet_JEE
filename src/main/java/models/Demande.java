package models;

import java.sql.Date;

public class Demande {
	    private int id;
	    private int id_user;
	    private int id_exemplaire;
	    private Date date_demande;
	    private String statut;
	    private Date date_retour_prevue;
	    private User user;
	    private exemplaire exemplaire;
	    
	    public Demande() {
	    	
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId_user() {
			return id_user;
		}

		public void setId_user(int id_user) {
			this.id_user = id_user;
		}

		public int getId_exemplaire() {
			return id_exemplaire;
		}

		public void setId_exemplaire(int id_exemplaire) {
			this.id_exemplaire = id_exemplaire;
		}

		public Date getDate_demande() {
			return date_demande;
		}

		public void setDate_demande(Date date_demande) {
			this.date_demande = date_demande;
		}

		public String getStatut() {
			return statut;
		}

		public void setStatut(String statut) {
			this.statut = statut;
		}

		public Date getDate_retour_prevue() {
			return date_retour_prevue;
		}

		public void setDate_retour_prevue(Date date_retour_prevue) {
			this.date_retour_prevue = date_retour_prevue;
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
