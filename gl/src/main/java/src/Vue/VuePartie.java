package src.Vue;

import src.Controlleur.AppControleur;
import src.Model.Partie;

public class VuePartie extends VueAbstraite {
	private Partie partie;
	private AppControleur controleur;

	public VuePartie(Partie partie, AppControleur controleur) {
		super();
		this.partie = partie;
		this.controleur = controleur;
		initUI();
	}

	private void initUI() {
		// Initialisation de l'interface utilisateur pour la vue de la partie
	}

	public void setControleur(AppControleur c) {
		this.controleur = c;
	}
}
