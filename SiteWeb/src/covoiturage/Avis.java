package covoiturage;

import javax.persistence.*;

@Entity
public class Avis {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public static int id;
	@OneToOne
	private TypeAvis type;
	private String texte;
	@ManyToOne
	private Client auteur;
	
	public Avis() {
	}

	public Avis(TypeAvis t,String texte, Client c){
		this.type=t;
		this.texte=texte;
		this.auteur=c;
	}

	public TypeAvis getType() {
		return type;
	}

	public void setType(TypeAvis type) {
		this.type = type;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Client getAuteur() {
		return auteur;
	}

	public void setAuteur(Client auteur) {
		this.auteur = auteur;
	}

}