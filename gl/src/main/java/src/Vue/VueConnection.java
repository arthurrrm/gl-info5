package src.Vue;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import src.Controlleur.AppControleur;

public class VueConnection extends VueAbstraite {

	private AppControleur appControleur;
	private JButton btnConnecter = new JButton("Se connecter");
	private JTextField champNomUtilisateur = new JTextField(20);

	public VueConnection(AppControleur appControleur) {
		this.appControleur = appControleur;
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.add(new JLabel("Nom d'utilisateur:"));
		panel.add(champNomUtilisateur);
		panel.add(btnConnecter);
		btnConnecter.addActionListener(e -> {
			String nomUtilisateur = champNomUtilisateur.getText();
			appControleur.seConnecter(nomUtilisateur);
		});
		setContentPane(panel);
	}

}
