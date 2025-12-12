package src.Model;

import java.util.Date;
import src.Model.StatusEpisode;

/**
 * Entité domaine Personnage.
 */
public class Personnage {
	private String nom;
	private String dateNaissance;
	private String profession;
	private Univers univers;
	private Biographie biographie;
	private Utilisateur proprietaire;
	private Utilisateur mj;
	private Utilisateur mjPropose;
	private StatusPersonnage statut;

	public Personnage(String nom, String dateNaissance, String profession, Univers univers, String biographieInitiale,
			Utilisateur proprietaire, Utilisateur mjPropose) {
		this.nom = nom;
		this.dateNaissance = dateNaissance; // Note: devrait être un type Date, mais String pour la simplicité du
											// formulaire
		this.profession = profession;
		this.univers = univers;
		this.proprietaire = proprietaire;
		this.mjPropose = mjPropose;
		this.statut = StatusPersonnage.EN_ATTENTE_MJ; // Statut initial
		this.mj = null;

		// Créer la biographie et le premier épisode "initial"
		this.biographie = new Biographie();
		if (biographieInitiale != null && !biographieInitiale.isEmpty()) {
			Episode episodeInitial = new Episode("Biographie initiale");
			episodeInitial.ajouterParagraphe(new Paragraphe(biographieInitiale, false));
			episodeInitial.setStatut(StatusEpisode.VALIDE); // La bio initiale est définitive
			this.biographie.ajouterEpisode(episodeInitial);
		}
	}

	public void ajouterEpisode(Episode episode) {
		biographie.ajouterEpisode(episode);
	}

	public void revelerSecret(Paragraphe paragraphe) {
		paragraphe.setEstSecret(false);
	}

	// Getters / Setters
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public Univers getUnivers() {
		return univers;
	}

	public void setUnivers(Univers univers) {
		this.univers = univers;
	}

	public Biographie getBiographie() {
		return biographie;
	}

	public Utilisateur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Utilisateur getMj() {
		return mj;
	}

	public void setMj(Utilisateur mj) {
		this.mj = mj;
	}

	public Utilisateur getMjPropose() {
		return mjPropose;
	}

	public void setMjPropose(Utilisateur mjPropose) {
		this.mjPropose = mjPropose;
	}

	public StatusPersonnage getStatut() {
		return statut;
	}

	public void setStatut(StatusPersonnage statut) {
		this.statut = statut;
	}

	@Override
	public String toString() {
		return this.nom + " (" + this.univers.getNom() + ")";
	}
}
