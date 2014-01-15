package beans;

import org.joda.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import covoiturage.Annonce;
import covoiturage.Avis;
import covoiturage.Client;
import covoiturage.Etat;
import covoiturage.Experience;
import covoiturage.Frequence;
import covoiturage.InformationsGenerales;
import covoiturage.Preferences;
import covoiturage.Reservation;
import covoiturage.Sexe;
import covoiturage.Trajet;
import covoiturage.TypeAnnonce;
import covoiturage.TypeAvis;
import covoiturage.Vehicule;

@Stateless
public class Inscription {
	//inscrire et posterAnnonce
	public static final String jour = "jour";
	public static final String mois = "mois";
	public static final String annee = "annee";
	
	//inscrire
	public static final String nom = "nom";
	public static final String prenom = "prenom";
	public static final String sexe = "sexe";
	public static final String email = "email";
	public static final String num = "numero";
	public static final String preferences = "preferences";
	public static final String vehicule = "vehicule";
	public static final String experience = "experience";
	
	//posterAnnonce
	public static final String textAnnonce = "text";
	public static final String posteur = "posteur";
	public static final String typeAnnonce = "typeAnnonce";
	public static final String preferencesAnnonce = "preferences";
	public static final String nombrePlace = "nombrePlace";
	public static final String prix = "prix";
	public static final String depart = "depart";
	public static final String arrivee = "arrivee";
	public static final String frequence = "frequence";
	public static final String etat = "etat";
	
	//Poster Avis
	public static final String typeAvis = "typeAvis";
	public static final String texteAvis = "texteAvis";
	
	//Poster Reveservation
	public static final String numeroAnnonce = "numeroAnnonce";
	
	
	@PersistenceContext
	private EntityManager em;

	public void inscrire(HttpServletRequest request) {
		String nom = request.getParameter(Inscription.nom);
		String prenom = request.getParameter(Inscription.prenom);
		Sexe sexe = covoiturage.Sexe.valueOf(request.getParameter(Inscription.sexe));
		int jour = Integer.parseInt(request.getParameter(Inscription.jour));
		int mois = Integer.parseInt(request.getParameter(Inscription.mois));
		int annee = Integer.parseInt(request.getParameter(Inscription.annee));
		String email = request.getParameter(Inscription.email);
		Experience exp = covoiturage.Experience.valueOf(request.getParameter(Inscription.experience));
		String[] pref = request.getParameterValues(Inscription.preferences);
		ArrayList<Preferences> preferences = new ArrayList<Preferences>();
		for(int i = 0; i < pref.length; i++) {
			preferences.add(covoiturage.Preferences.valueOf(pref[i]));
		}
		Vehicule vehicule = new Vehicule(request.getParameter(Inscription.vehicule));
		@SuppressWarnings("deprecation")
		Date d = new Date(annee + 1900, mois, jour);
		//Ajout de nouvelles Info generales
		InformationsGenerales iG = new InformationsGenerales();
		iG.setNom(prenom);
		iG.setNom(nom);
		iG.setSexe(sexe);
		iG.setDate(d);
		iG.setPref(preferences);
		iG.setMail(email);
		iG.setV(vehicule);
		em.persist(iG);
		
		//Ajout d'un Client
		Client c = new Client();
		c.setInfo(iG);
		c.setExp(exp);
		c.setAvis(new ArrayList<Avis>());
		c.setAmis(new ArrayList<Client>());
		c.setBannis(new ArrayList<Client>());
		c.setMesTrajets(new ArrayList<Trajet>());
		em.persist(c);

		//Ajout de preference,sexe,vehicule,experience, vehicule
		em.persist(preferences);
		em.persist(sexe);
		em.persist(exp);
		em.persist(vehicule);
	}
	
	public void posterAnnonce(HttpServletRequest request, HttpSession session) {
		//Ajout de l'annonce
		String textAnnonce = request.getParameter(this.textAnnonce);
		Client posteur = (Client) em.find(covoiturage.Client.class,session.getId());
		TypeAnnonce typeAnnonce = covoiturage.TypeAnnonce.valueOf(request.getParameter(this.typeAnnonce));
		String[] pref = request.getParameterValues(this.preferencesAnnonce);
		ArrayList<Preferences> preferences = new ArrayList<>();
		for(int i = 0; i < pref.length; i++) {
			preferences.add(covoiturage.Preferences.valueOf(pref[i]));
		}
		String depart = request.getParameter(this.depart);
		String arrivee = request.getParameter(this.arrivee);
		Frequence frequence = covoiturage.Frequence.valueOf(request.getParameter(this.frequence));
		Etat etat = covoiturage.Etat.valueOf(request.getParameter(this.etat));
		int nombrePlace = Integer.parseInt(request.getParameter(this.nombrePlace));
		int prix = Integer.parseInt(request.getParameter(this.prix));
		int jour = Integer.parseInt(request.getParameter(this.jour));
		int mois = Integer.parseInt(request.getParameter(this.mois));
		int annee = Integer.parseInt(request.getParameter(this.annee));
		@SuppressWarnings("deprecation")
		Date d = new Date(annee + 1900, mois, jour);
		
		//Ajout d'une annonce
		Annonce annonce = new Annonce();
		annonce.setTexte(textAnnonce);
		annonce.setPosteur(posteur);
		annonce.setType(typeAnnonce);
		annonce.setPref(preferences);
		annonce.setNbPlace(nombrePlace);
		annonce.setPrix(prix);
		annonce.setNbPlace(nombrePlace);
		annonce.setDate(d);
		em.persist(annonce);
		
		//Ajouter le trajet de l'annonce
		Trajet trajet = new Trajet();
		trajet.setDate(d);
		trajet.setDepart(depart);
		trajet.setArrivee(arrivee);
		trajet.setConducteur(posteur);
		trajet.setFreq(frequence);
		trajet.setEtat(etat);
		trajet.getAnnonce().add(annonce);
		em.persist(trajet);
		
		//Mise a jour de l'annonce on lui ajoute son annonce
		annonce.setTrajet(trajet);
		em.merge(annonce);
		
		//Ajout de l'annonce d'une annonce au client à faire
		posteur.getMesTrajets().add(trajet);
		em.merge(posteur);
		
		//Ajout d'un type d'annonce, de la frequence, de l'etat, preferences
		em.persist(typeAnnonce);
		em.persist(frequence);
		em.persist(etat);
		em.persist(preferences);
	}
	
	public void posterAvis(HttpServletRequest request, HttpSession session) {

		//Ajout d'un avis
		String texteAvis = request.getParameter(this.texteAvis);
		Client c = (Client) em.find(covoiturage.Client.class,session.getId());
		TypeAvis typeAvis = covoiturage.TypeAvis.valueOf(request.getParameter(this.typeAvis));
		
		//Ajout de l'avis
		Avis avis = new Avis();
		avis.setType(typeAvis);
		avis.setTexte(texteAvis);
		avis.setAuteur(c);
		em.persist(avis);
		
		//Ajout d'un type d'avis
		em.persist(typeAvis);
		
		//Ajout de l'avis du client posteur
		c.getAvis().add(avis);
		em.merge(c);
	}

	public void posterReservation(HttpServletRequest request, HttpSession session) {
		Client reserveur = (Client) em.find(covoiturage.Client.class,session.getId());
		int numeroAnnonce = Integer.parseInt(request.getParameter(this.numeroAnnonce));
		Annonce annonce = (Annonce) em.find(covoiturage.Annonce.class,numeroAnnonce);
		int nombrePlace = Integer.parseInt(request.getParameter(this.nombrePlace));
		
		//Regarder si il y'a des nombre de places suffisants
		if(nombrePlace > annonce.getNbPlace()) {
			//Dispatcher vers servlet en ajoutant un param d'erreur
			//request.setAttribute("nombrePlaceGrand", request);
		} else {
			annonce.setNbPlace(annonce.getNbPlace()-nombrePlace);
			//Ajouter la reservation
			Reservation reservation = new Reservation();
			reservation.setAnnonce(annonce);
			reservation.setNbPlaces(nombrePlace);
			reservation.setVoyageur(reserveur);
			em.persist(reservation);
			
			//Mise à jour de la reservation(modification du nombre de place)
			em.merge(annonce);
			
			
			
			//Mise à jour du client reserveur
			reserveur.getMesTrajets().add(annonce.getTrajet());
			em.merge(reserveur);
			
			//Mise a jour de la liste d'annonce du trajet
			Trajet trajet = annonce.getTrajet();
			//Mettre à jour les autres annonces
			for (Annonce a : trajet.getAnnonce()) {
				a.setNbPlace(a.getNbPlace()-nombrePlace);
				em.merge(a);
			}
			trajet.getAnnonce().add(annonce);
			em.merge(trajet);
		}
	}

	public void changerEtat(HttpServletRequest request, HttpSession session) {
		Client posteur = (Client) em.find(covoiturage.Client.class,session.getId());
		int numeroAnnonce = Integer.parseInt(request.getParameter(this.numeroAnnonce));
		Annonce annonce = (Annonce) em.find(covoiturage.Annonce.class,numeroAnnonce);
		Trajet trajet = annonce.getTrajet();
		if(annonce.getNbPlace() == 0) {
			trajet.setEtat(Etat.effectué);
			em.merge(trajet);
		}
	}
}
