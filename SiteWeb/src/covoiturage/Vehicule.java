package covoiturage;

import javax.persistence.*;

@Entity
public class Vehicule {
	
	@Id
	private int id;
	private String nom;
	
	public Vehicule() {
	}
	
	public Vehicule (String nom){
		this.setNom(nom);
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}

}
