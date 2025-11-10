package src.Controlleur;

import src.Model.FacadeModele;
import src.Model.Univers;

import java.util.Map;

/**
 * Contrôleur pour opérations liées aux parties.
 */
public class PartieControleur {
	private final FacadeModele modele;

	public PartieControleur(FacadeModele modele) {
		this.modele = modele;
	}

	public void traiterPropositionPartie(Map<String, String> donnees, src.Model.Utilisateur utilisateurConnecte) {
		// Extraction minimale (titre, univers...) - squelette
		String titre = donnees.get("titre");
		String universNom = donnees.get("univers");
		Univers univers = modele.getUnivers(universNom);
		modele.proposerPartie(titre, univers, utilisateurConnecte);
	}
}
