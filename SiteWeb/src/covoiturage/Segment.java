package covoiturage;

import javax.persistence.*;

@Entity
public class Segment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public static int id;
	private String depart;
	private String arrivee;
	@OneToOne
	private Trajet pere;
	
	public Segment() {
	}
	
	public Segment(String depart,String arrivee, Trajet pere){
		this.depart=depart;
		this.arrivee=arrivee;
		this.pere=pere;
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

	public Trajet getPere() {
		return pere;
	}

	public void setPere(Trajet pere) {
		this.pere = pere;
	}

}