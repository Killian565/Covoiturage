package covoiturage;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

@Entity
public class InformationsGenerales {
	
	private String nom; 
	private String prenom;
	@OneToOne
	private Sexe sexe;
	@OneToOne
	private Date date; 
	private String mail;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int num;
	@OneToMany
	private ArrayList<Preferences> pref;
	@OneToOne
	private Vehicule voiture;
	
	public InformationsGenerales(){
	}
	
	public InformationsGenerales (String nom,String prenom,Sexe sexe,
			Date date,String mail, int num,ArrayList<Preferences> pref,
			Vehicule v){
		this.nom=nom;
		this.prenom=prenom;
		this.date=date;
		this.sexe=sexe;
		this.mail=mail;
		this.num=num;
		this.pref=pref;
		this.voiture=v;
		
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Sexe getSexe() {
		return sexe;
	}

	public void setSexe(Sexe sexe) {
		this.sexe = sexe;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public ArrayList<Preferences> getPref() {
		return pref;
	}

	public void setPref(ArrayList<Preferences> pref) {
		this.pref = pref;
	}

	public Vehicule getV() {
		return voiture;
	}

	public void setV(Vehicule v) {
		this.voiture = v;
	}

}