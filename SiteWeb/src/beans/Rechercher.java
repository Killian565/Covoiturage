package beans;

import java.util.ArrayList;
import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.jboss.netty.handler.codec.http.HttpResponse;

import covoiturage.Annonce;
import covoiturage.Trajet;

@Stateless
public class Rechercher {
	//chercher une date de depart
	public static final String jour = "jour";
	public static final String mois = "mois";
	public static final String annee = "annee";
	//Chercher destination
	public static final String depart = "depart";
	public static final String arrivee = "arrivee";
	
	@PersistenceContext
	private EntityManager em;
	
	public void chercherTrajet(HttpServletRequest request) {
		String depart = request.getParameter(this.depart);
		String arrivee = request.getParameter(this.arrivee);
		int jour = Integer.parseInt(request.getParameter(Inscription.jour));
		int mois = Integer.parseInt(request.getParameter(Inscription.mois));
		int annee = Integer.parseInt(request.getParameter(Inscription.annee));
		@SuppressWarnings("deprecation")
		Date d = new Date(annee + 1900, mois, jour);
		Query q = em.createQuery("SELECT o FROM covoiturage.Trajet o where depart='" + depart + "' and arrivee='" + arrivee + "'");
		
		ArrayList<Trajet> resultatTrajet = (ArrayList<Trajet>) q.getResultList();
		//Envoie de la liste de trajet
		request.setAttribute("resultatRechercheTrajet", resultatTrajet);
		
		//Envoie de la liste des annonces
		ArrayList<ArrayList<Annonce>> annonces = new ArrayList<>();
		for (Trajet t : resultatTrajet) {
			annonces.add(t.getAnnonce());
		}
		
		//Envoie des annonces
		request.setAttribute("annonces", annonces);
	}

}
