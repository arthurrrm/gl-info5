package src.Vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import src.Controlleur.AppControleur;
import src.Controlleur.PersonnageControleur;
import src.Model.Personnage;

public class VueDemandesMJ extends VueAbstraite {
    private AppControleur appControleur;
    private PersonnageControleur personnageControleur;

    private JList<Personnage> listeDemandes;
    private DefaultListModel<Personnage> listModel;
    private JButton btnAccepter = new JButton("Accepter");
    private JButton btnRefuser = new JButton("Refuser");
    private JButton btnConsulter = new JButton("Consulter la bio");
    private JButton btnRetour = new JButton("Retour");

    public VueDemandesMJ() {
        setTitle("Demandes de prise en charge MJ");
        initUI();
        setSize(700, 500);
        setLocationRelativeTo(null);
    }

    private void initUI() {
        listModel = new DefaultListModel<>();
        listeDemandes = new JList<>(listModel);

        JPanel panelBoutons = new JPanel(new GridLayout(4, 1, 5, 5));
        panelBoutons.add(btnConsulter);
        panelBoutons.add(btnAccepter);
        panelBoutons.add(btnRefuser);
        panelBoutons.add(btnRetour);

        add(new JScrollPane(listeDemandes), BorderLayout.CENTER);
        add(panelBoutons, BorderLayout.EAST);

        // Listeners
        btnRetour.addActionListener(e -> appControleur.afficherTableauDeBord());

        btnAccepter.addActionListener(e -> {
            Personnage selection = listeDemandes.getSelectedValue();
            if (selection != null) {
                personnageControleur.traiterAcceptationPersonnage(selection);
            }
        });

        btnRefuser.addActionListener(e -> {
            Personnage selection = listeDemandes.getSelectedValue();
            if (selection != null) {
                int choix = JOptionPane.showConfirmDialog(this,
                        "Voulez-vous vraiment refuser ce personnage ?\nIl sera supprimé.", "Confirmation",
                        JOptionPane.YES_NO_OPTION);
                if (choix == JOptionPane.YES_OPTION) {
                    personnageControleur.traiterRefusPersonnage(selection);
                }
            }
        });

        // Le bouton consulter est un bonus pour voir les détails avant de décider
        btnConsulter.addActionListener(e -> {
            Personnage selection = listeDemandes.getSelectedValue();
            if (selection != null) {
                JOptionPane.showMessageDialog(this,
                        "Nom: " + selection.getNom() + "\n" +
                        "Date de Naissance: " + selection.getDateNaissance() + "\n" +
                        "Profession: " + selection.getProfession() + "\n" +
                        "Univers: " + selection.getUnivers().getNom() + "\n" +
                        "Biographie:\n" + selection.getBiographie(),
                        "Détails du Personnage",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public void setControleurs(AppControleur appCtrl, PersonnageControleur persoCtrl) {
        this.appControleur = appCtrl;
        this.personnageControleur = persoCtrl;
    }

    public void setDemandes(List<Personnage> demandes) {
        listModel.clear();
        for (Personnage p : demandes) {
            listModel.addElement(p);
        }
    }
}