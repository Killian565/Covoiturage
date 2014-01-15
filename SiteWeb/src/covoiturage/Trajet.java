package covoiturage;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Trajet {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	@OneToOne
	private Date date;
	private int duree;//A mettre ou pas!?
	private String depart;
	private String arrivee;
	@OneToMany
	private ArrayList<Annonce> annonce;
	@OneToOne
	private Client conducteur;
	@OneToOne
	private Frequence freq;
	@OneToOne
	private Etat etat;
	
	public Trajet() {
	}
	
	public Trajet(Date date,int duree,String depart,
			String arrivee,Client conducteur,Frequence freq){
		this.depart=depart;
		this.arrivee=arrivee;
		this.conducteur=conducteur;
		this.date=date;
		this.duree=duree;
		this.freq=freq;
		this.setEtat(Etat.pasEncoreEffecuté);
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	public String getArrivee() {
		return arrivee;
	}

	public void setArrivee(String arrivee) {
		this.arrivee = arrivee;
	}

	public Client getConducteur() {
		return conducteur;
	}

	public void setConducteur(Client conducteur) {
		this.conducteur = conducteur;
	}

	public Frequence getFreq() {
		return freq;
	}

	public void setFreq(Frequence freq) {
		this.freq = freq;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}
	public ArrayList<Annonce> getAnnonce() {
		return annonce;
	}
	public void setAnnonce(ArrayList<Annonce> annonce) {
		this.annonce = annonce;
	}
}
