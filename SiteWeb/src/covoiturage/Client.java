package covoiturage;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.persistence.*;

import java.util.List;

@Entity
public class Client {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public  int id;
	@OneToOne
	private InformationsGenerales info;
	@OneToOne
	private Experience exp;
	@OneToMany
	private ArrayList<Avis> avis;
	@ManyToMany
	private ArrayList<Client> amis;
	@ManyToMany
	private ArrayList<Client> bannis;
	@ManyToMany
	private ArrayList<Trajet> mesTrajets;
	
	
	
	public Client() {
		
	}
	
	public Client(InformationsGenerales info) {
		super();
		this.info = info;
	}
	public  int getId() {
		return id;
	}
	public  void setId(int id) {
		this.id = id;
	}
	public InformationsGenerales getInfo() {
		return info;
	}
	public void setInfo(InformationsGenerales info) {
		this.info = info;
	}
	public Experience getExp() {
		return exp;
	}
	public void setExp(Experience exp) {
		this.exp = exp;
	}
	public ArrayList<Avis> getAvis() {
		return avis;
	}
	public void setAvis(ArrayList<Avis> avis) {
		this.avis = avis;
	}
	public ArrayList<Client> getAmis() {
		return amis;
	}
	public void setAmis(ArrayList<Client> amis) {
		this.amis = amis;
	}
	public ArrayList<Client> getBannis() {
		return bannis;
	}
	public void setBannis(ArrayList<Client> bannis) {
		this.bannis = bannis;
	}
	public ArrayList<Trajet> getMesTrajets() {
		return mesTrajets;
	}
	public void setMesTrajets(ArrayList<Trajet> mesTrajets) {
		this.mesTrajets = mesTrajets;
	}
	
	
}