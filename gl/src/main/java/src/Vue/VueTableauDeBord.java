package src.Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import src.App;
import src.Controlleur.AppControleur;
import src.Model.Partie;
import src.Model.Personnage;

/**
 * Vue tableau de bord listant les personnages et actions principales.
 */
public class VueTableauDeBord extends VueAbstraite {
	private JButton btnCreerPerso = new JButton("Créer personnage");
	private JButton btnProposerPartie = new JButton("Proposer partie");
	private JList<Personnage> listePersonnages = new JList<Personnage>();
	private AppControleur controleur;
	private ArrayList<JButton> boutonsPartie = new ArrayList<JButton>();

	public VueTableauDeBord(AppControleur controleur) {
		super();
		this.controleur = controleur;
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel actions = new JPanel();
		actions.add(btnCreerPerso);
		btnCreerPerso.addActionListener(e -> controleur.afficherCreationPersonnage());
		actions.add(btnProposerPartie);
		btnProposerPartie.addActionListener(e -> controleur.afficherPropositionPartie());
		panel.add(actions, BorderLayout.NORTH);
		panel.add(new JScrollPane(listePersonnages), BorderLayout.CENTER);
		setContentPane(panel);
		JPanel panelBoutonsPartie = new JPanel();
		List<Partie> parties = controleur.getPartiesUtilisateur();
		parties.sort((p1, p2) -> {
			return p1.getStatut().compareTo(p2.getStatut());
		});
		for (Partie partie : parties) {
			JButton btnPartie = new JButton(
					"Partie: " + partie.getTitre() + ", univers: " + partie.getUnivers().getNom() + " ("
							+ partie.getStatut() + ")");
			btnPartie.addActionListener(e -> controleur.afficherPartie(partie));
			boutonsPartie.add(btnPartie);
			panelBoutonsPartie.add(btnPartie);
		}
		panel.add(panelBoutonsPartie, BorderLayout.CENTER);

	}

	public void setControleur(AppControleur c) {
		this.controleur = c;
		// Listeners à ajouter plus tard
	}
}
