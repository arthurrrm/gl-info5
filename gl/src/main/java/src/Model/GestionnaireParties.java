package src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des parties.
 */
public class GestionnaireParties {
	private final List<Partie> parties = new ArrayList<Partie>();

	public Partie creerPartie(String titre, Univers univers, Utilisateur maitreJeu) {
		Partie partie = new Partie(titre, univers, maitreJeu);
		parties.add(partie);
		System.out.println("Partie créée : " + titre + " dans l'univers " + univers.getNom() + " par "
				+ maitreJeu.getNom());
		return partie;
	}

	public void ajouterParticipant(Partie partie, Personnage perso) {
		partie.ajouterParticipant(perso);
	}

	public void finaliserPartie(Partie partie, String resume) {
		partie.finaliser(resume);
	}

	public List<Partie> getParties() {
		return parties;
	}
}
