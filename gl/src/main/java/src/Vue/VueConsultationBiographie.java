package src.Vue;

import javax.swing.*;
import java.awt.*;
import src.Model.Personnage;
import src.Model.StatusEpisode;
import src.Model.Paragraphe;
import src.App;
import src.Controlleur.AppControleur;
import src.Model.Episode;

import java.util.Iterator;
import java.util.List;

/**
 * Vue de consultation de la biographie d'un personnage.
 */
public class VueConsultationBiographie extends VueAbstraite {
	AppControleur controleur;

	public VueConsultationBiographie(AppControleur controleur) {
		super();
		setLayout(new BorderLayout());
		this.controleur = controleur;

	}

	public void afficherBiographie(Personnage p, boolean fullAccess) {
		this.setTitle("Biographie de " + p.getNom());
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setAutoscrolls(true);
		List<Episode> episodes = p.getBiographie().getEpisodesChronologiques();
		int numEpisode = 1;
		for (int i = 0; i < episodes.size(); i++) {
			if (episodes.get(i).getStatut() == StatusEpisode.BROUILLON && !fullAccess) {
				continue;
			}
			boolean episodeVisible = fullAccess;
			Iterator<Paragraphe> itPara = episodes.get(i).getContenu().iterator();
			while (itPara.hasNext() && !episodeVisible) {
				Paragraphe para = itPara.next();
				if (!para.isEstSecret()) {
					episodeVisible = true;
				}
			}
			if (!episodeVisible) {
				continue;
			}

			JPanel episodePanel = new JPanel();
			episodePanel.setLayout(new BoxLayout(episodePanel, BoxLayout.Y_AXIS));
			episodePanel.setBorder(BorderFactory.createTitledBorder("Épisode " + (numEpisode++) + ": "
					+ episodes.get(i).getTitre() + " (" + episodes.get(i).getDateCreation() + ") - Statut: "
					+ episodes.get(i).getStatut()));
			for (Paragraphe para : episodes.get(i).getContenu()) {
				JLabel labelPara = new JLabel(para.isEstSecret() ? "[Paragraphe secret]" : "Paragraphe:");
				JPanel episodeParaPanel = new JPanel(new BorderLayout());
				episodeParaPanel.add(labelPara, BorderLayout.WEST);
				episodeParaPanel.setMaximumSize(labelPara.getPreferredSize());
				episodePanel.add(episodeParaPanel);
				if (!para.isEstSecret() || fullAccess) {
					JTextArea textePara = new JTextArea(para.getTexte());
					textePara.setLineWrap(true);
					textePara.setWrapStyleWord(true);
					textePara.setEditable(false);
					episodePanel.add(textePara);

					if (episodes.get(i).getStatut() == StatusEpisode.BROUILLON) {
						JButton btnEditer = new JButton("Éditer le paragraphe");
						btnEditer.addActionListener(e -> {
							String nouveauTexte = JOptionPane.showInputDialog(
									this,
									"Modifier le paragraphe :",
									para.getTexte());
							if (nouveauTexte != null && !nouveauTexte.isEmpty()) {
								para.setTexte(nouveauTexte);
								textePara.setText(nouveauTexte);
							}
						});
						episodePanel.add(btnEditer);
					}
				}
			}
			if (fullAccess && episodes.get(i).getStatut() == StatusEpisode.BROUILLON) {
				final int ifinal = i;
				JButton btnAjouterParagraphe = new JButton("Ajouter un paragraphe");
				btnAjouterParagraphe.addActionListener(e -> {
					String textePara = JOptionPane.showInputDialog(
							this,
							"Texte du nouveau paragraphe :",
							"Nouveau Paragraphe");
					String estSecret = JOptionPane.showInputDialog(
							this,
							"Le paragraphe est-il secret ? (oui/non) :",
							"non");
					if (textePara != null && !textePara.isEmpty()) {
						Paragraphe nouveauPara = new Paragraphe(textePara, estSecret.equalsIgnoreCase("oui"));
						episodes.get(ifinal).ajouterParagraphe(nouveauPara);
						this.dispose();
						VueConsultationBiographie nouvelleVue = new VueConsultationBiographie(controleur);
						nouvelleVue.afficherBiographie(p, fullAccess);
					}
				});
				episodePanel.add(btnAjouterParagraphe);

				JButton btnPublierEpisode = new JButton("Publier l'épisode");
				btnPublierEpisode.addActionListener(e -> {
					episodes.get(ifinal).setStatut(StatusEpisode.VALIDE);
					this.dispose();
					VueConsultationBiographie nouvelleVue = new VueConsultationBiographie(controleur);
					nouvelleVue.afficherBiographie(p, fullAccess);
				});
				episodePanel.add(btnPublierEpisode);

			}
			panel.add(episodePanel);
		}
		if (fullAccess) {
			JButton btnAjouterEpisode = new JButton("Ajouter un nouvel épisode");
			btnAjouterEpisode.addActionListener(e -> {
				String titreEpisode = JOptionPane.showInputDialog(
						this,
						"Titre de l'épisode :",
						"Nouvel Épisode");
				String dateEpisode = JOptionPane.showInputDialog(
						this,
						"Date de l'épisode (aaaa/mm/jj) :",
						"DateNonSpecifiee");
				if (titreEpisode != null && !titreEpisode.isEmpty()) {
					Episode nouvelEpisode = new Episode(titreEpisode, dateEpisode, null);
					p.ajouterEpisode(nouvelEpisode);
					this.dispose();
					VueConsultationBiographie nouvelleVue = new VueConsultationBiographie(controleur);
					nouvelleVue.afficherBiographie(p, fullAccess);
				}
			});
			panel.add(btnAjouterEpisode);
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		this.add(scrollPane, BorderLayout.CENTER);
		this.setSize(600, 400);
		this.setVisible(true);
	}
}
