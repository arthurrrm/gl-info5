package src.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Façade exposant des opérations de haut niveau sur le modèle.
 */
public class FacadeModele {
	private final GestionnairePersonnages gestionnairePersonnages;
	private final GestionnaireParties gestionnaireParties;
	private final UniversManager universManager;
	private final UtilisateurManager utilisateurManager;

	public FacadeModele() {
		this.gestionnairePersonnages = new GestionnairePersonnages();
		this.gestionnaireParties = new GestionnaireParties();
		this.universManager = new UniversManager();
		this.utilisateurManager = new UtilisateurManager();
	}

	/**
	 * Charge des données de démonstration depuis des CSV placés dans
	 * src/main/resources/demo.
	 */
	public void initDemo() {
		try {
			chargerUniversDepuisCsv("demo/univers.csv");
			chargerUtilisateursDepuisCsv("demo/utilisateurs.csv");
			chargerPersonnagesDepuisCsv("demo/personnages.csv");
			chargerPartiesDepuisCsv("demo/parties.csv");
		} catch (Exception e) {
			System.err.println("Erreur chargement données demo: " + e.getMessage());
		}
	}

	private void chargerUniversDepuisCsv(String path) throws Exception {
		List<String> lignes = lireRessourceLignes(path);
		for (String l : lignes) {
			String line = l.trim();
			if (line.isEmpty() || line.startsWith("#"))
				continue;
			addUnivers(line);
		}
	}

	private void chargerUtilisateursDepuisCsv(String path) throws Exception {
		List<String> lignes = lireRessourceLignes(path);
		for (String l : lignes) {
			String line = l.trim();
			if (line.isEmpty() || line.startsWith("#"))
				continue;
			utilisateurManager.connecterUtilisateur(line);
		}
	}

	private void chargerPersonnagesDepuisCsv(String path) throws Exception {
		List<String> lignes = lireRessourceLignes(path);
		for (String l : lignes) {
			String line = l.trim();
			if (line.isEmpty() || line.startsWith("#"))
				continue;
			String[] p = line.split(";");
			if (p.length < 7)
				continue;
			String nom = p[0];
			String dateNaissance = p[1];
			String profession = p[2];
			String universNom = p[3];
			String mjNom = p[4];
			String proprietaireNom = p[5];
			String bio = p[6];
			Utilisateur proprietaire = getOuCreerUtilisateur(proprietaireNom);
			Utilisateur mj = getOuCreerUtilisateur(mjNom);
			creerPersonnage(nom, dateNaissance, profession, universNom, bio, proprietaire, mj);
		}
	}

	private void chargerPartiesDepuisCsv(String path) throws Exception {
		List<String> lignes = lireRessourceLignes(path);
		for (String l : lignes) {
			String line = l.trim();
			if (line.isEmpty() || line.startsWith("#"))
				continue;
			String[] p = line.split(";");
			if (p.length < 3)
				continue;
			String titre = p[0];
			String universNom = p[1];
			String mjNom = p[2];
			String paragrapheInitial = p[3];
			String lieu = p[4];
			String dateStr = p[5];
			Univers univers = getUnivers(universNom);
			Utilisateur mj = getOuCreerUtilisateur(mjNom);
			Paragraphe situationInitiale = new Paragraphe(paragrapheInitial, false);
			proposerPartie(titre, univers, mj, situationInitiale, lieu, dateStr);
		}
	}

	private Utilisateur getOuCreerUtilisateur(String nom) {
		Utilisateur u = getUtilisateurParNom(nom);
		if (u == null) {
			u = connecterUtilisateur(nom);
		}
		return u;
	}

	private List<String> lireRessourceLignes(String resourcePath) throws Exception {
		java.io.InputStream is = getClass().getClassLoader().getResourceAsStream(resourcePath);
		if (is == null)
			throw new IllegalArgumentException("Ressource introuvable: " + resourcePath);
		List<String> lignes = new java.util.ArrayList<String>();
		try (java.io.BufferedReader br = new java.io.BufferedReader(
				new java.io.InputStreamReader(is, java.nio.charset.StandardCharsets.UTF_8))) {
			String line;
			while ((line = br.readLine()) != null) {
				lignes.add(line);
			}
		}
		return lignes;
	}

	public Personnage creerPersonnage(String nom, String dateNaissance, String profession, String universNom,
			String biographieInitiale, Utilisateur proprietaire, Utilisateur mjPropose) {
		System.out.println("Création du personnage '" + nom + "' pour " + proprietaire.getNom());
		Univers univers = getUnivers(universNom);
		Personnage nouveauPersonnage = new Personnage(nom, dateNaissance, profession, univers, biographieInitiale,
				proprietaire, mjPropose);
		gestionnairePersonnages.ajouter(nouveauPersonnage);

		return nouveauPersonnage;
	}

	public Partie proposerPartie(String titre, Univers univers, Utilisateur maitreJeu, Paragraphe situationInitiale,
			String lieu, String dateStr) {
		return gestionnaireParties.creerPartie(titre, univers, maitreJeu, situationInitiale, lieu, dateStr);
	}

	public Utilisateur connecterUtilisateur(String nomUtilisateur) {

		return utilisateurManager.connecterUtilisateur(nomUtilisateur);
	}

	public Univers getUnivers(String nom) {
		return universManager.getOrCreate(nom);
	}

	public List<Personnage> getPersonnagesUtilisateur(Utilisateur user, Boolean mj) {
		// Filtrage simple sur propriétaire
		List<Personnage> result = new ArrayList<Personnage>();
		for (Personnage p : gestionnairePersonnages.getPersonnages()) {
			if (p.getProprietaire().equals(user) || (mj && p.getMj() != null && p.getMj().equals(user))) {
				result.add(p);
			}
		}
		return result;
	}

	public List<Partie> getPartiesDisponibles() {
		return new ArrayList<Partie>(gestionnaireParties.getParties());
	}

	public List<String> getUniversDisponibles() {
		return universManager.getNomsUnivers();
	}

	public void addUnivers(String nomUnivers) {
		universManager.getOrCreate(nomUnivers);
	}

	// Methodes pour le Mj
	public List<Personnage> getDemandesEnAttente(Utilisateur mj) {
		return gestionnairePersonnages.getPersonnages().stream()
				.filter(p -> p.getStatut() == StatusPersonnage.EN_ATTENTE_MJ && p.getMjPropose().equals(mj))
				.collect(Collectors.toList());
	}

	public void accepterPersonnage(Personnage personnage) {
		personnage.setStatut(StatusPersonnage.ACTIF);
		personnage.setMj(personnage.getMjPropose()); // Le MJ proposé devient le MJ officiel
		personnage.setMjPropose(null);
	}

	public void refuserPersonnage(Personnage personnage) {
		gestionnairePersonnages.getPersonnages().remove(personnage);
	}

	public List<String> getNomsUtilisateurs() {
		return utilisateurManager.getNomsUtilisateurs();
	}

	public Utilisateur getUtilisateurParNom(String nomUtilisateur) {
		return utilisateurManager.getUtilisateurParNom(nomUtilisateur);
	}
}
