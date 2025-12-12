package src.Controlleur;

import src.Model.FacadeModele;
import src.Model.Personnage;
import src.Model.Utilisateur;

import java.util.Map;

import javax.swing.JOptionPane;

/**
 * Contrôleur pour la gestion des personnages.
 */
public class PersonnageControleur {
	private final FacadeModele modele;
	private final AppControleur appControleur; // Référence au contrôleur principal

	// Constructeur mis à jour
	public PersonnageControleur(FacadeModele modele, AppControleur appControleur) {
		this.modele = modele;
		this.appControleur = appControleur;
	}

   // Méthode mise à jour pour accepter les données complètes et le propriétaire
    public void traiterCreationPersonnage(Map<String, String> donnees, Utilisateur proprietaire) {
        String nom = donnees.get("nom");
        String dateNaissance = donnees.get("dateNaissance");
        String profession = donnees.get("profession");
        String universNom = donnees.get("univers");
		String biographieInitiale = donnees.get("biographieInitiale");
		String mjProposeNom = donnees.get("mjPropose");
        
		
        // Validation simple (peut être enrichie)
        if (nom == null || nom.trim().isEmpty() || universNom == null) {
			JOptionPane.showMessageDialog(null, "Le nom et l'univers sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
		
		Utilisateur mjPropose = modele.getUtilisateurParNom(mjProposeNom); // Méthode à créer dans le modèle
		if (mjPropose == null) {
			JOptionPane.showMessageDialog(null, "Le MJ sélectionné n'existe pas.", "Erreur", JOptionPane.ERROR_MESSAGE);
			return;
		}


		modele.creerPersonnage(nom, dateNaissance, profession, universNom, biographieInitiale, proprietaire, mjPropose);
        
        // Après la création, on retourne au tableau de bord
        appControleur.afficherTableauDeBord();
    }

	public void traiterAcceptationPersonnage(Personnage personnage) {
		modele.accepterPersonnage(personnage);
		// Rafraîchir la vue pour enlever la demande traitée
		appControleur.afficherDemandesMJ();
	}

	public void traiterRefusPersonnage(Personnage personnage) {
		modele.refuserPersonnage(personnage);
		// Rafraîchir la vue
		appControleur.afficherDemandesMJ();
	}
}
