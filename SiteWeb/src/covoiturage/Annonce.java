package covoiturage;


import java.util.ArrayList;
import java.util.Date;

import javax.ejb.*;
import javax.persistence.*;

@Entity 
public class Annonce {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String texte;
	@ManyToOne
	private Client posteur;
	@OneToOne
	private TypeAnnonce type;
	@OneToMany
	private ArrayList<Preferences> pref;
	private Trajet trajet;
	private int nbPlace;
	private float prix;
	private Date dateCreation;

	public Annonce() {
	}
	
	public Annonce (String t,Client posteur,TypeAnnonce type,float prix,
			ArrayList<Preferences> pref,int nbPlace, Trajet trajet){
		this.texte=t;
		this.posteur=posteur;
		this.type=type;
		this.pref=pref;
		this.nbPlace=nbPlace;
		this.setPrix(prix);
		this.trajet = trajet;
	}

	public Trajet getTrajet() {
		return trajet;
	}

	public void setTrajet(Trajet trajet) {
		this.trajet = trajet;
	}

	public Client getPosteur() {
		return posteur;
	}

	public void setPosteur(Client posteur) {
		this.posteur = posteur;
	}

	public TypeAnnonce getType() {
		return type;
	}

	public void setType(TypeAnnonce type) {
		this.type = type;
	}

	public ArrayList<Preferences> getPref() {
		return pref;
	}

	public void setPref(ArrayList<Preferences> pref) {
		this.pref = pref;
	}

	public int getNbPlace() {
		return nbPlace;
	}

	public void setNbPlace(int nbPlace) {
		this.nbPlace = nbPlace;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Date getDate() {
		return dateCreation;
	}

	public void setDate(Date date) {
		this.dateCreation = date;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

}