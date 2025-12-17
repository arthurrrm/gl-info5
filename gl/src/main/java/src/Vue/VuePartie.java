package src.Vue;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import src.Controlleur.AppControleur;
import src.Model.Partie;
import src.Model.Personnage;
import src.Model.StatusPartie;

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

		JPanel panel = new JPanel();
		panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
		JPanel info = new JPanel();
		info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
		info.setAlignmentX(Component.LEFT_ALIGNMENT);
		info.add(new JLabel("Partie: " + partie.getTitre()));
		info.add(new JLabel("Univers: " + partie.getUnivers().getNom()));
		info.add(new JLabel("Maitre du jeu: " + partie.getMaitreJeu().getNom()));
		info.add(new JLabel("Situation initiale: " + partie.getSituationInitiale().getTexte()));
		info.add(new JLabel("Lieu: " + partie.getLieu()));
		info.add(new JLabel("Date: " + partie.getDateStr()));
		panel.add(info);
		JPanel statutPanel = new JPanel();
		statutPanel.setLayout(new BoxLayout(statutPanel, BoxLayout.Y_AXIS));
		statutPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		statutPanel.add(new JLabel("Statut: " + partie.getStatut()));
		if ((partie.getStatut().equals(StatusPartie.PROPOSEE)) &&
				partie.getMaitreJeu().equals(controleur.getUtilisateurConnecte())) {

			JButton btnDemarrer = new JButton("Démarrer la partie");
			btnDemarrer.addActionListener(e -> {
				partie.setStatut(StatusPartie.EN_COURS);
				JOptionPane.showMessageDialog(null,
						"La partie a été démarrée.");
				this.dispose();
				controleur.afficherPartie(partie);
			});
			statutPanel.add(btnDemarrer);
		} else if (partie.getStatut().equals(StatusPartie.EN_COURS) &&
				partie.getMaitreJeu().equals(controleur.getUtilisateurConnecte())) {
			JButton btnClore = new JButton("Clore la partie");
			btnClore.addActionListener(e -> {
				String resume = JOptionPane.showInputDialog("Entrez le résumé final de la partie:");
				if (resume != null && !resume.trim().isEmpty()) {
					partie.finaliser(resume);
					JOptionPane.showMessageDialog(null,
							"La partie a été clôturée.");
					this.dispose();
					controleur.afficherPartie(partie);
				} else {
					JOptionPane.showMessageDialog(null,
							"Le résumé final ne peut pas être vide.");
				}
			});
			statutPanel.add(btnClore);
		}

		panel.add(statutPanel);
		JPanel joueursPanel = new JPanel();
		joueursPanel.setLayout(new javax.swing.BoxLayout(joueursPanel, javax.swing.BoxLayout.Y_AXIS));
		joueursPanel.add(new JLabel("Listes des personnages:"));
		List<Personnage> personnages = partie.getParticipants();
		for (Personnage p : personnages) {
			joueursPanel.add(new JLabel("- " + p.getNom()));
		}
		JButton btnAjouterPerso = new JButton("Ajouter un personnage");
		btnAjouterPerso.setEnabled(partie.getStatut().equals(StatusPartie.PROPOSEE));
		btnAjouterPerso.addActionListener(e -> {
			List<Personnage> persosDisponibles = controleur
					.getPersonnages(controleur.getUtilisateurConnecte().equals(partie.getMaitreJeu()));
			ArrayList<Personnage> persosNonParticipants = new ArrayList<>();
			for (Personnage p : persosDisponibles) {
				if (!personnages.contains(p) && p.getUnivers().equals(partie.getUnivers())) {
					persosNonParticipants.add(p);
				}
			}
			if (persosNonParticipants.size() > 0) {
				String[] options = persosNonParticipants.stream()
						.map(Personnage::getNom)
						.toArray(String[]::new);
				String selected = (String) JOptionPane.showInputDialog(
						null,
						"Sélectionnez un personnage:",
						"Ajouter un personnage",
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[0]);

				if (selected != null) {
					for (Personnage p : persosNonParticipants) {
						if (p.getNom().equals(selected)) {
							partie.ajouterParticipant(p);
							JOptionPane.showMessageDialog(null,
									"Personnage " + p.getNom() + " ajouté à la partie.");
							// Refresh the view
							this.dispose();
							controleur.afficherPartie(partie);
							break;
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(null,
						"Aucun personnage disponible pour être ajouté à cette partie.");
			}

		});
		joueursPanel.add(btnAjouterPerso);
		panel.add(joueursPanel);
		JButton btnFermer = new JButton("Fermer");
		btnFermer.addActionListener(e -> {
			this.dispose();
			controleur.afficherTableauDeBord();
		});
		btnFermer.setAlignmentX(Component.LEFT_ALIGNMENT);
		panel.add(btnFermer);

		setContentPane(panel);
	}

	public void setControleur(AppControleur c) {
		this.controleur = c;
	}

}
