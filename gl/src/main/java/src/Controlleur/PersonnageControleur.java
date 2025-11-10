package src.Controlleur;

import src.Model.FacadeModele;
import src.Model.Personnage;
import src.Model.Utilisateur;

import java.util.Map;

/**
 * Contrôleur pour la gestion des personnages.
 */
public class PersonnageControleur {
	private final FacadeModele modele;

	public PersonnageControleur(FacadeModele modele) {
		this.modele = modele;
	}

	public void traiterCreationPersonnage(Map<String, String> donnees) {
		String nom = donnees.get("nom");
		String universNom = donnees.get("univers");
		// Autres champs à extraire plus tard.
		modele.creerPersonnage(nom, null); // Univers null en squelette
	}

	public void traiterDemandeChangementMJ(Personnage perso, Utilisateur nouveauMJ) {
		// Délégation future vers gestionnaire
		// modele.changerMJ(perso, nouveauMJ); (méthode potentielle)
	}
}
