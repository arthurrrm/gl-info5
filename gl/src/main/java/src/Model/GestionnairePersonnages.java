package src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des personnages.
 */
public class GestionnairePersonnages {
	private final List<Personnage> personnages = new ArrayList<Personnage>();

	public Personnage creerPersonnage(String nom, Univers univers) {
		Personnage p = new Personnage(nom, univers);
		personnages.add(p);
		return p;
	}

	public void validerEpisode(Episode episode, Utilisateur user) {
		// Validation future
	}

	public void changerMJ(Personnage perso, Utilisateur nouveauMJ) {
		perso.setMj(nouveauMJ);
	}

	public List<Personnage> getPersonnages() {
		return personnages;
	}
}
