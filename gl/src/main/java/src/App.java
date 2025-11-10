package src;

import src.Controlleur.AppControleur;
import src.Model.FacadeModele;

/**
 * Point d'entrée minimal pour initialiser le modèle et le contrôleur principal.
 */
public class App {
    public static void main(String[] args) {
        FacadeModele modele = new FacadeModele();
        AppControleur appControleur = new AppControleur(modele);
        appControleur.demarrerApplication();
    }
}
