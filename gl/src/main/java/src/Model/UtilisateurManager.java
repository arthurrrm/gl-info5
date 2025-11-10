package src.Model;

import java.util.HashMap;
import java.util.Map;

public class UtilisateurManager {

	private Map<String, Utilisateur> utilisateurs = new HashMap<>();

	public Utilisateur connecterUtilisateur(String nom) {
		Utilisateur utilisateur = utilisateurs.get(nom);
		if (utilisateur == null) {
			utilisateur = new Utilisateur(nom);
			utilisateurs.put(nom, utilisateur);
		}
		return utilisateur;
	}

}
