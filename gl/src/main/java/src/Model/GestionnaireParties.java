package src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des parties.
 */
public class GestionnaireParties {
	private final List<Partie> parties = new ArrayList<Partie>();

	public Partie creerPartie(String titre, Univers univers) {
		Partie partie = new Partie(titre, univers);
		parties.add(partie);
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
