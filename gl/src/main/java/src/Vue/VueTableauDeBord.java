package src.Vue;

import javax.swing.*;
import java.awt.*;
import src.Controlleur.AppControleur;
import src.Model.Personnage;

/**
 * Vue tableau de bord listant les personnages et actions principales.
 */
public class VueTableauDeBord extends VueAbstraite {
	private JButton btnCreerPerso = new JButton("Créer personnage");
	private JButton btnProposerPartie = new JButton("Proposer partie");
	private JList<Personnage> listePersonnages = new JList<Personnage>();
	private AppControleur controleur;

	public VueTableauDeBord() {
		super();
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel actions = new JPanel();
		actions.add(btnCreerPerso);
		actions.add(btnProposerPartie);
		panel.add(actions, BorderLayout.NORTH);
		panel.add(new JScrollPane(listePersonnages), BorderLayout.CENTER);
		setContentPane(panel);
	}

	public void setControleur(AppControleur c) {
		this.controleur = c;
		// Listeners à ajouter plus tard
	}
}
