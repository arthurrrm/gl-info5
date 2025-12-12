package src.Model;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Gestion des utilisateurs (connexion simple par nom).
 */
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

    public List<String> getNomsUtilisateurs() {
        return List.copyOf(utilisateurs.keySet());
    }

    public Utilisateur getUtilisateurParNom(String nom) {
        return utilisateurs.get(nom);
    }

}
