package src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Façade exposant des opérations de haut niveau sur le modèle.
 */
public class FacadeModele {
	private final GestionnairePersonnages gestionnairePersonnages;
	private final GestionnaireParties gestionnaireParties;

	public FacadeModele() {
		this.gestionnairePersonnages = new GestionnairePersonnages();
		this.gestionnaireParties = new GestionnaireParties();
	}

	public Personnage creerPersonnage(String nom, Univers univers) {
		return gestionnairePersonnages.creerPersonnage(nom, univers);
	}

	public Partie proposerPartie(String titre, Univers univers) {
		return gestionnaireParties.creerPartie(titre, univers);
	}

	public List<Personnage> getPersonnagesUtilisateur(Utilisateur user) {
		// Filtrage simple sur propriétaire
		List<Personnage> result = new ArrayList<Personnage>();
		for (Personnage p : gestionnairePersonnages.getPersonnages()) {
			if (p.getProprietaire() == user) {
				result.add(p);
			}
		}
		return result;
	}

	public List<Partie> getPartiesDisponibles() {
		return new ArrayList<Partie>(gestionnaireParties.getParties());
	}
}
