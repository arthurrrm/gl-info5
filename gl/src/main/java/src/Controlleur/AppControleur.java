package src.Controlleur;

import src.Model.FacadeModele;
import src.Model.Partie;
import src.Model.Personnage;
import src.Model.Utilisateur;
import src.Vue.IVue;
import src.Vue.VueConnection;
import src.Vue.VueCreationPartie;
import src.Vue.VueTableauDeBord;
import src.Vue.VueCreationPersonnage;
import src.Vue.VueDemandesMJ;
import src.Vue.VuePartie;
import src.Vue.VueConsultationBiographie;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

/**
 * Contrôleur principal orchestrant la navigation entre vues et l'accès à la
 * façade.
 */
public class AppControleur {
	private final FacadeModele modele;
	private final PersonnageControleur personnageCtrl;
	private final PartieControleur partieCtrl;
	private IVue vueCourante;
	Utilisateur utilisateurConnecte;

	public AppControleur(FacadeModele modele) {
		this.modele = modele;
		this.personnageCtrl = new PersonnageControleur(modele, this);
		this.partieCtrl = new PartieControleur(modele);
	}

	public void demarrerApplication() {
		afficherConnexionUtilisateur();
	}

	public void afficherTableauDeBord() {
		VueTableauDeBord vue = new VueTableauDeBord(this);
		changerVue(vue);
	}

	public void afficherCreationPersonnage() {
		VueCreationPersonnage vue = new VueCreationPersonnage();
		// On passe les deux contrôleurs à la vue
		vue.setControleurs(this, personnageCtrl);
		changerVue(vue);
	}

	public void afficherPropositionPartie() {
		VueCreationPartie vue = new VueCreationPartie();
		vue.setAppControleur(this);
		changerVue(vue);
	}

	public void afficherConnexionUtilisateur() {
		VueConnection vue = new VueConnection(this);
		changerVue(vue);
	}

	public void afficherPartie(Partie partie) {
		VuePartie vue = new VuePartie(partie, this);
		changerVue(vue);
	}

	public void seConnecter(String nomUtilisateur) {
		utilisateurConnecte = modele.connecterUtilisateur(nomUtilisateur);
	}

	private void changerVue(IVue nouvelle) {
		if (vueCourante != null) {
			vueCourante.fermer();
		}
		vueCourante = nouvelle;
		vueCourante.afficher();
	}

	// Renommé et clarifié pour mieux correspondre à son rôle
	public void creerPersonnageDepuisVue(Map<String, String> donnees) {
		// Le contrôleur principal passe le contexte (utilisateur connecté)
		// au contrôleur spécialisé.
		personnageCtrl.traiterCreationPersonnage(donnees, utilisateurConnecte);
	}

	public void creerPartieDepuisDonnees(Map<String, String> donnees) {
		partieCtrl.traiterPropositionPartie(donnees, utilisateurConnecte);
	}

	public List<String> getUniversDisponibles() {
		return modele.getUniversDisponibles();
	}

	public void addUnivers(String nomUnivers) {
		modele.addUnivers(nomUnivers);
	}

	public List<String> getNomsUtilisateurs() {
		List<String> utilisateurs = modele.getNomsUtilisateurs();
		return utilisateurs;
	}

	public void afficherDemandesMJ() {
		VueDemandesMJ vue = new VueDemandesMJ();
		vue.setControleurs(this, personnageCtrl);
		// On charge les demandes pour l'utilisateur actuellement connecté
		List<Personnage> demandes = modele.getDemandesEnAttente(utilisateurConnecte);
		vue.setDemandes(demandes);
		changerVue(vue);
	}

	public void afficherBiographiePersonnage(Personnage personnage, boolean fullAccess) {
		VueConsultationBiographie vue = new VueConsultationBiographie(this);
		vue.afficherBiographie(personnage, fullAccess);
		vue.afficher();

	}

	public List<Partie> getPartiesUtilisateur() {
		return modele.getPartiesDisponibles();
	}

	public Utilisateur getUtilisateurConnecte() {
		return utilisateurConnecte;
	}

	public List<Personnage> getPersonnages(Boolean mj) {
		return modele.getPersonnagesUtilisateur(utilisateurConnecte, mj);
	}

	public List<Personnage> getPersonnagesUtilisateur() {
		// Renvoie tous les personnages, actifs ou en attente, pour l'affichage
		return modele.getPersonnagesUttilisateur(utilisateurConnecte); // Nouvelle méthode à créer dans le modèle
	}

	public void lancerChangementMJ(Personnage personnage) {
		// Récupérer la liste des MJs potentiels (tous les utilisateurs sauf le joueur
		// lui-même et le MJ actuel)
		List<String> choixMJ = modele.getNomsUtilisateurs().stream()
				.filter(nom -> !nom.equals(utilisateurConnecte.getNom()) && !nom.equals(personnage.getMj().getNom()))
				.collect(Collectors.toList());

		if (choixMJ.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Aucun autre MJ n'est disponible pour un transfert.", "Information",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		String nouveauMJNom = (String) JOptionPane.showInputDialog(
				null,
				"Choisissez le nouveau MJ pour " + personnage.getNom() + " :",
				"Demande de changement de MJ",
				JOptionPane.PLAIN_MESSAGE,
				null,
				choixMJ.toArray(),
				choixMJ.get(0));

		if (nouveauMJNom != null && !nouveauMJNom.isEmpty()) {
			personnageCtrl.traiterDemandeChangementMJ(personnage, nouveauMJNom);
		}
	}

	/**
	 * Gère la déconnexion de l'utilisateur.
	 */
	public void deconnecter() {
		System.out.println("Déconnexion de l'utilisateur : "
				+ (utilisateurConnecte != null ? utilisateurConnecte.getNom() : "aucun"));

		this.utilisateurConnecte = null;
		afficherConnexionUtilisateur();
	}

}
