package src.Vue;

import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.App;
import src.Controlleur.AppControleur;
import src.Controlleur.PartieControleur;

public class VueCreationPartie extends VueAbstraite {
	private JButton btnValider = new JButton("Valider");
	private JButton btnAnnuler = new JButton("Annuler");
	private JTextField champNomPartie = new JTextField(20);
	private JTextField champUnivers = new JTextField(20);
	private PartieControleur partieControleur;
	private AppControleur appControleur;

	public VueCreationPartie() {
		super();
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5)); // 3 lignes, 2 colonnes, espaces de 5px

		panel.add(new JLabel("Nom de la partie :"));
		panel.add(champNomPartie);

		panel.add(new JLabel("Univers :"));
		panel.add(champUnivers);

		panel.add(btnValider);
		btnValider.addActionListener(e -> {
			// Récupérer les données et appeler le contrôleur de partie
			String nomPartie = champNomPartie.getText();
			String univers = champUnivers.getText();
			partieControleur.traiterPropositionPartie(Map.of(
					"titre", nomPartie,
					"univers", univers));
			appControleur.afficherTableauDeBord();
		});
		panel.add(btnAnnuler);
		btnAnnuler.addActionListener(e -> appControleur.afficherTableauDeBord());

		setContentPane(panel);
	}

	public void setPartieControleur(PartieControleur partieCtrl) {
		this.partieControleur = partieCtrl;
	}

	public void setAppControleur(AppControleur appCtrl) {
		this.appControleur = appCtrl;
	}

}
