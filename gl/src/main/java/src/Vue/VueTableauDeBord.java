package src.Vue;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import src.App;
import src.Controlleur.AppControleur;
import src.Model.Partie;
import src.Model.Personnage;
import src.Model.StatusPersonnage;

/**
 * Vue tableau de bord listant les personnages et actions principales.
 */
public class VueTableauDeBord extends VueAbstraite {
	private JButton btnCreerPerso = new JButton("Créer personnage");
	private JButton btnProposerPartie = new JButton("Proposer partie");
	private JButton btnGererDemandes = new JButton("Gérer les demandes MJ");
	private JButton btnVoirBiographie = new JButton("Voir biographie");
	private JList<Personnage> listePersonnages = new JList<Personnage>();
	private AppControleur controleur;
	private ArrayList<JButton> boutonsPartie = new ArrayList<JButton>();
	private DefaultListModel<Personnage> listModel;
	private JButton btnChangerMJ = new JButton("Changer de MJ");
	private JButton btnDeconnexion = new JButton("Se déconnecter");

	public VueTableauDeBord(AppControleur controleur) {
		super();
		this.controleur = controleur;
		List<Personnage> personnages = controleur.getPersonnages(true);
		listModel = new DefaultListModel<>();
		for (Personnage p : personnages) {
			listModel.addElement(p);
		}
		initUI();
	}

	private void initUI() {
		JPanel panel = new JPanel(new BorderLayout());
		JPanel actions = new JPanel();
		listePersonnages = new JList<>(listModel);
		listePersonnages.addListSelectionListener(e -> {
			if (!e.getValueIsAdjusting()) {
				Personnage selection = listePersonnages.getSelectedValue();
				if (selection != null) {
					if (selection.getMj() == null
							|| !(selection.getProprietaire() == controleur.getUtilisateurConnecte())) {
						btnChangerMJ.setEnabled(false);
					} else {
						btnChangerMJ.setEnabled(true);
					}
					btnVoirBiographie.setEnabled(true);
				} else {
					btnChangerMJ.setEnabled(false);
					btnVoirBiographie.setEnabled(false);
				}

			}
		});
		actions.add(btnCreerPerso);
		btnCreerPerso.addActionListener(e -> controleur.afficherCreationPersonnage());
		actions.add(btnProposerPartie);
		btnProposerPartie.addActionListener(e -> controleur.afficherPropositionPartie());
		actions.add(btnGererDemandes);

		btnChangerMJ.addActionListener(e -> {
			Personnage selection = listePersonnages.getSelectedValue();
			if (selection != null) {
				controleur.lancerChangementMJ(selection);
			} else {
				JOptionPane.showMessageDialog(this, "Veuillez sélectionner un personnage.", "Aucune sélection",
						JOptionPane.WARNING_MESSAGE);
			}
		});
		actions.add(btnDeconnexion);
		btnDeconnexion.addActionListener(e -> {
			controleur.deconnecter();
		});
		panel.add(actions, BorderLayout.NORTH);
		JPanel panelSud = new JPanel();
		JPanel panelActionSud = new JPanel();
		panelSud.setLayout(new BoxLayout(panelSud, BoxLayout.Y_AXIS));
		btnGererDemandes.addActionListener(e -> controleur.afficherDemandesMJ());
		btnVoirBiographie.addActionListener(e -> {
			Personnage selection = listePersonnages.getSelectedValue();
			if (selection != null) {
				controleur.afficherBiographiePersonnage(selection, true);
			} else {
				JOptionPane.showMessageDialog(this, "Veuillez sélectionner un personnage.", "Aucune sélection",
						JOptionPane.WARNING_MESSAGE);
			}
		});
		btnVoirBiographie.setEnabled(false);
		btnChangerMJ.setEnabled(false);
		panelActionSud.add(btnChangerMJ);
		panelActionSud.add(btnVoirBiographie);
		panelSud.add(panelActionSud);
		panelSud.add(new JScrollPane(listePersonnages));
		panel.add(panelSud, BorderLayout.SOUTH);

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
}
