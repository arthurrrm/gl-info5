package src.Vue;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import src.Controlleur.PersonnageControleur;

/**
 * Formulaire de création de personnage.
 */
public class VueCreationPersonnage extends VueAbstraite {
	private JTextField champNom = new JTextField(20);
	private JButton btnValider = new JButton("Valider");
	private PersonnageControleur controleur;

	public VueCreationPersonnage() {
		super();
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel("Nom:"));
		panel.add(champNom);
		panel.add(btnValider);
		setContentPane(panel);
	}

	public Map<String, String> getDonneesFormulaire() {
		Map<String, String> donnees = new HashMap<String, String>();
		donnees.put("nom", champNom.getText());
		// univers / autres champs plus tard
		return donnees;
	}

	public void setControleur(PersonnageControleur c) {
		this.controleur = c;
		// Listener bouton à ajouter plus tard
	}
}
