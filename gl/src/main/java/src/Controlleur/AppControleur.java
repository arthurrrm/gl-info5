package src.Controlleur;

import src.Model.FacadeModele;
import src.Vue.IVue;
import src.Vue.VueTableauDeBord;
import src.Vue.VueCreationPersonnage;

import java.util.Map;

/**
 * Contrôleur principal orchestrant la navigation entre vues et l'accès à la façade.
 */
public class AppControleur {
	private final FacadeModele modele;
	private final PersonnageControleur personnageCtrl;
	private final PartieControleur partieCtrl;
	private IVue vueCourante;

	public AppControleur(FacadeModele modele) {
		this.modele = modele;
		this.personnageCtrl = new PersonnageControleur(modele);
		this.partieCtrl = new PartieControleur(modele);
	}

	public void demarrerApplication() {
		afficherTableauDeBord();
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
}
