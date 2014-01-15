package covoiturage;

import javax.persistence.*;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public static int id;
	@OneToOne
	private Client voyageur;
	@OneToOne
	private Annonce annonce; 
	private int nbPlaces;
	
	public Reservation() {
	}
	
	public Reservation(Client voyageur, Annonce annonce,
			int nbPlaces){
		this.voyageur=voyageur;
		this.annonce=annonce;
		this.nbPlaces=nbPlaces;
	}
	
	public Client getVoyageur() {
		return voyageur;
	}
	
	public void setVoyageur(Client voyageur) {
		this.voyageur = voyageur;
	}
	
	public Annonce getAnnonce() {
		return annonce;
	}
	
	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
	
	public int getNbPlaces() {
		return nbPlaces;
	}
	
	public void setNbPlaces(int nbPlaces) {
		this.nbPlaces = nbPlaces;
	}
}