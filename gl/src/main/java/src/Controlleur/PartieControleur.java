package src.Controlleur;

import src.Model.FacadeModele;
import src.Model.Paragraphe;
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
		String titre = donnees.get("titre");
		String universNom = donnees.get("univers");
		Univers univers = modele.getUnivers(universNom);
		Paragraphe situationInitiale = new Paragraphe(donnees.get("situationInitiale"), false);
		String lieu = donnees.get("lieu");
		String dateStr = donnees.get("date");
		modele.proposerPartie(titre, univers, utilisateurConnecte, situationInitiale, lieu, dateStr);
	}
}
