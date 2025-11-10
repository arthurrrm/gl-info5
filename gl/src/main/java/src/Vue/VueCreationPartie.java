package src.Vue;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Controlleur.PartieControleur;

public class VueCreationPartie extends VueAbstraite {
	private JButton btnValider = new JButton("Valider");
	private JButton btnAnnuler = new JButton("Annuler");
	private JTextField champNomPartie = new JTextField(20);
	private JTextField champUnivers = new JTextField(20);
	private PartieControleur controleur;

	public VueCreationPartie() {
		super();
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new java.awt.GridLayout(3, 1, 5, 5));
		JPanel row1 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		row1.add(new JLabel("Nom de la partie :"));
		row1.add(champNomPartie);
		JPanel row2 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
		row2.add(new JLabel("Univers :"));
		row2.add(champUnivers);
		JPanel row3 = new JPanel(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER));
		row3.add(btnValider);
		row3.add(btnAnnuler);
		panel.add(row1);
		panel.add(row2);
		panel.add(row3);
		setContentPane(panel);
	}

	public void setControleur(PartieControleur partieCtrl) {
		this.controleur = partieCtrl;
	}

}
