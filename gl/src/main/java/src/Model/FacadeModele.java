package src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Façade exposant des opérations de haut niveau sur le modèle.
 */
public class FacadeModele {
	private final GestionnairePersonnages gestionnairePersonnages;
	private final GestionnaireParties gestionnaireParties;
	private final UniversManager universManager;
	private final UtilisateurManager utilisateurManager;

	public FacadeModele() {
		this.gestionnairePersonnages = new GestionnairePersonnages();
		this.gestionnaireParties = new GestionnaireParties();
		this.universManager = new UniversManager();
		this.utilisateurManager = new UtilisateurManager();
	}

	public Personnage creerPersonnage(String nom, Univers univers) {
		return gestionnairePersonnages.creerPersonnage(nom, univers);
	}

	public Partie proposerPartie(String titre, Univers univers, Utilisateur maitreJeu, Paragraphe situationInitiale,
			String lieu, String dateStr) {
		return gestionnaireParties.creerPartie(titre, univers, maitreJeu, situationInitiale, lieu, dateStr);
	}

	public Utilisateur connecterUtilisateur(String nomUtilisateur) {

		return utilisateurManager.connecterUtilisateur(nomUtilisateur);
	}

	public Univers getUnivers(String nom) {
		return universManager.getOrCreate(nom);
	}

	public List<Personnage> getPersonnagesUtilisateur(Utilisateur user, Boolean mj) {
		// Filtrage simple sur propriétaire
		List<Personnage> result = new ArrayList<Personnage>();
		for (Personnage p : gestionnairePersonnages.getPersonnages()) {
			if (p.getProprietaire().equals(user) || (mj && p.getMj().equals(user))) {
				result.add(p);
			}
		}
		return result;
	}

	public List<Partie> getPartiesDisponibles() {
		return new ArrayList<Partie>(gestionnaireParties.getParties());
	}

	public List<String> getUniversDisponibles() {
		return universManager.getNomsUnivers();
	}

	public void addUnivers(String nomUnivers) {
		universManager.getOrCreate(nomUnivers);
	}
}
