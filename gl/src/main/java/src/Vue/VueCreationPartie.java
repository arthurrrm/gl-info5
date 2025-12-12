package src.Vue;

import java.awt.GridLayout;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.App;
import src.Controlleur.AppControleur;
import src.Controlleur.PartieControleur;

public class VueCreationPartie extends VueAbstraite {
	private JButton btnValider = new JButton("Valider");
	private JButton btnAnnuler = new JButton("Annuler");
	private JTextField champNomPartie = new JTextField(20);
	private JComboBox<String> champUnivers = new JComboBox<String>();
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
			String univers = (String) champUnivers.getSelectedItem();
			if (univers.equals("Nouveau")) {
				String nomUnivers = JOptionPane.showInputDialog("Veuillez entrer le nom de l'univers :");
				if (nomUnivers != null && !nomUnivers.trim().isEmpty()) {
					appControleur.addUnivers(nomUnivers.trim());
					univers = nomUnivers.trim();
				} else {
					// Annuler la création de la partie si le nom de l'univers est invalide
					JOptionPane.showMessageDialog(this, "Le nom de l'univers ne peut pas être vide.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			appControleur.creerPartieDepuisDonnees(Map.of(
					"titre", nomPartie,
					"univers", univers));
			appControleur.afficherTableauDeBord();
		});
		panel.add(btnAnnuler);
		btnAnnuler.addActionListener(e -> appControleur.afficherTableauDeBord());

		setContentPane(panel);
	}

	public void setAppControleur(AppControleur appCtrl) {
		this.appControleur = appCtrl;
		List<String> universDisponibles = appControleur.getUniversDisponibles();
		universDisponibles.add("Nouveau");
		champUnivers.setModel(new DefaultComboBoxModel<>(universDisponibles.toArray(new String[0])));
	}

}
