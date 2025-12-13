package src.Vue;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import src.Controlleur.AppControleur;
import src.Controlleur.PersonnageControleur;
import src.Model.Personnage;

public class VueDemandesTransfert extends VueAbstraite {
    private JList<Personnage> listeDemandes = new JList<>();
    private DefaultListModel<Personnage> listModel = new DefaultListModel<>();
    private JButton btnAccepter = new JButton("Accepter transfert");
    private JButton btnRefuser = new JButton("Refuser transfert");
    private JButton btnRetour = new JButton("Retour");

    private AppControleur appControleur;
    private PersonnageControleur personnageControleur;

    public VueDemandesTransfert() {
        super();
        initUI();
    }

    public void setControleurs(AppControleur app, PersonnageControleur persoCtrl) {
        this.appControleur = app;
        this.personnageControleur = persoCtrl;
    }

    public void setDemandes(List<Personnage> demandes) {
        listModel.clear();
        for (Personnage p : demandes) {
            listModel.addElement(p);
        }
        listeDemandes.setModel(listModel);
    }

    private void initUI() {
        JPanel root = new JPanel(new BorderLayout());
        listeDemandes.setModel(listModel);
        root.add(new JScrollPane(listeDemandes), BorderLayout.CENTER);

        JPanel actions = new JPanel();
        actions.add(btnAccepter);
        actions.add(btnRefuser);
        actions.add(btnRetour);

        btnAccepter.addActionListener(e -> {
            Personnage selection = listeDemandes.getSelectedValue();
            if (selection != null) {
                personnageControleur.traiterAcceptationTransfert(selection);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une demande.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnRefuser.addActionListener(e -> {
            Personnage selection = listeDemandes.getSelectedValue();
            if (selection != null) {
                personnageControleur.traiterRefusTransfert(selection);
            } else {
                JOptionPane.showMessageDialog(this, "Veuillez sélectionner une demande.", "Aucune sélection", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnRetour.addActionListener(e -> appControleur.afficherTableauDeBord());

        root.add(actions, BorderLayout.SOUTH);
        setContentPane(root);
    }
}
