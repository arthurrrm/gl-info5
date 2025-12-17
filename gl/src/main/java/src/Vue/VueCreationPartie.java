package src.Vue;

import java.awt.GridLayout;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
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
	private JTextField champSituationInitiale = new JTextField(200);
	private JTextField champLieu = new JTextField(200);
	private JTextField champDate = new JTextField("jj/mm/aaaa", 20);
	private AppControleur appControleur;

	public VueCreationPartie() {
		super();
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new GridLayout(6, 2, 5, 5)); // 6 lignes, 2 colonnes, espaces de 5px

		panel.add(new JLabel("Nom de la partie :"));
		panel.add(champNomPartie);

		panel.add(new JLabel("Univers :"));

		panel.add(champUnivers);

		panel.add(new JLabel("Situation initiale :"));
		panel.add(champSituationInitiale);

		panel.add(new JLabel("Lieu :"));
		panel.add(champLieu);
		panel.add(new JLabel("Date (aaaa/mm/jj) :"));
		panel.add(champDate);

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
					JOptionPane.showMessageDialog(this, "Le nom de l'univers ne peut pas être vide.", "Erreur",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
			appControleur.creerPartieDepuisDonnees(Map.of(
					"titre", nomPartie,
					"univers", univers,
					"situationInitiale", champSituationInitiale.getText(),
					"lieu", champLieu.getText(),
					"date", champDate.getText()));
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
