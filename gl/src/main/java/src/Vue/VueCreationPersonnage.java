package src.Vue;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

import src.Controlleur.AppControleur;
import src.Controlleur.PersonnageControleur;

/**
 * Formulaire de création de personnage.
 */
public class VueCreationPersonnage extends VueAbstraite {

	private AppControleur appControleur;
	private PersonnageControleur personnageControleur;

	// Champs du formulaire
	private JTextField champNom = new JTextField(20);
	private JTextField champDateNaissance = new JTextField(20);
	private JTextField champProfession = new JTextField(20);
	private JComboBox<String> comboUnivers = new JComboBox<>();
	private JTextArea areaBioInitiale = new JTextArea(5, 20);
	private JComboBox<String> comboMJ = new JComboBox<>();

	// Boutons
	private JButton btnValider = new JButton("Valider la création");
	private JButton btnAnnuler = new JButton("Annuler");

	public VueCreationPersonnage() {
		super();
		setTitle("Création d'un nouveau personnage");
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.anchor = GridBagConstraints.WEST;

		// Ligne Nom
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("Nom :"), gbc);
		gbc.gridx = 1;
		panel.add(champNom, gbc);

		// Ligne Date de Naissance
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Date de Naissance :"), gbc);
		gbc.gridx = 1;
		panel.add(champDateNaissance, gbc);

		// Ligne Profession
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(new JLabel("Profession :"), gbc);
		gbc.gridx = 1;
		panel.add(champProfession, gbc);

		// Ligne Univers
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(new JLabel("Univers :"), gbc);
		gbc.gridx = 1;
		panel.add(comboUnivers, gbc);

		// Ligne MJ Proposé
		gbc.gridx = 0;
		gbc.gridy = 4; // S'insère avant la biographie
		panel.add(new JLabel("Proposer à (MJ) :"), gbc);
		gbc.gridx = 1;
		panel.add(comboMJ, gbc);

		// Ligne Biographie Initiale
		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		panel.add(new JLabel("Biographie Initiale :"), gbc);
		gbc.gridx = 1;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		panel.add(new JScrollPane(areaBioInitiale), gbc);

		// Ligne Boutons
		JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		panelBoutons.add(btnAnnuler);
		panelBoutons.add(btnValider);
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		panel.add(panelBoutons, gbc);

		// Ajout des listeners
		btnValider.addActionListener(e -> {
			appControleur.creerPersonnageDepuisVue(getDonneesFormulaire());
		});

		btnAnnuler.addActionListener(e -> {
			appControleur.afficherTableauDeBord();
		});

		setContentPane(panel);
		pack();
		setLocationRelativeTo(null);
	}

	public Map<String, String> getDonneesFormulaire() {
		Map<String, String> donnees = new HashMap<>();
		donnees.put("nom", champNom.getText());
		donnees.put("dateNaissance", champDateNaissance.getText());
		donnees.put("profession", champProfession.getText());
		donnees.put("univers", (String) comboUnivers.getSelectedItem());
		donnees.put("biographieInitiale", areaBioInitiale.getText());
		donnees.put("mjPropose", (String) comboMJ.getSelectedItem());
		return donnees;
	}

	public void setControleurs(AppControleur appCtrl, PersonnageControleur persoCtrl) {
		this.appControleur = appCtrl;
		this.personnageControleur = persoCtrl;

		List<String> universDisponibles = appCtrl.getUniversDisponibles();
		comboUnivers.setModel(new DefaultComboBoxModel<>(universDisponibles.toArray(new String[0])));

		List<String> utilisateurs = appCtrl.getNomsUtilisateurs();
		comboMJ.setModel(new DefaultComboBoxModel<>(utilisateurs.toArray(new String[0])));
	}
}