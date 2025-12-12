package src.Controlleur;

import src.Model.FacadeModele;
import src.Model.Utilisateur;
import src.Vue.IVue;
import src.Vue.VueConnection;
import src.Vue.VueCreationPartie;
import src.Vue.VueTableauDeBord;
import src.Vue.VueCreationPersonnage;

import java.util.List;
import java.util.Map;

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
		this.personnageCtrl = new PersonnageControleur(modele);
		this.partieCtrl = new PartieControleur(modele);
	}

	public void demarrerApplication() {
		afficherConnexionUtilisateur();
	}

	public void afficherTableauDeBord() {
		VueTableauDeBord vue = new VueTableauDeBord();
		vue.setControleur(this);
		changerVue(vue);
	}

	public void afficherCreationPersonnage() {
		VueCreationPersonnage vue = new VueCreationPersonnage();
		vue.setControleur(personnageCtrl);
		changerVue(vue);
	}

	public void afficherPropositionPartie() {
		VueCreationPartie vue = new VueCreationPartie();
		vue.setAppControleur(this);
		changerVue(vue);
	}

	public void afficherConnexionUtilisateur() {
		VueConnection vue = new VueConnection(this);
		vue.setAppControleur(this);
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

	// Facilite le passage de données issues des vues si nécessaire
	public void creerPersonnageDepuisDonnees(Map<String, String> donnees) {
		personnageCtrl.traiterCreationPersonnage(donnees);
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
}
