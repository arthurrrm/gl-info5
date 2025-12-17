package src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Service de gestion des personnages.
 */
public class GestionnairePersonnages {
	private final List<Personnage> personnages = new ArrayList<Personnage>();

	public void ajouter(Personnage perso) {
		personnages.add(perso);
		System.out.println("Personnage ajouté : " + perso.getNom() + " dans l'univers " + perso.getUnivers().getNom());
	}

	public void changerMJ(Personnage perso, Utilisateur nouveauMJ) {
		perso.setMj(nouveauMJ);
	}

	public List<Personnage> getPersonnages() {
		return personnages;
	}
}
