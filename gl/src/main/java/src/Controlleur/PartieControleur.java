package src.Controlleur;

import src.Model.FacadeModele;
import java.util.Map;

/**
 * Contrôleur pour opérations liées aux parties.
 */
public class PartieControleur {
	private final FacadeModele modele;

	public PartieControleur(FacadeModele modele) {
		this.modele = modele;
	}

	public void traiterPropositionPartie(Map<String, String> donnees) {
		// Extraction minimale (titre, univers...) - squelette
		String titre = donnees.get("titre");
		String universNom = donnees.get("univers");
		// Univers / autres champs à récupérer dans une implémentation future
		modele.proposerPartie(titre, null); // Univers null pour l'instant (squelette)
	}
}
