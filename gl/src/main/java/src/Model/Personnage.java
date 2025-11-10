package src.Model;

import java.util.Date;

/**
 * Entité domaine Personnage.
 */
public class Personnage {
	private String nom;
	private Date dateNaissance; 
	private String profession;
	private Univers univers;
	private Biographie biographie;
	private Utilisateur proprietaire;
	private Utilisateur mj;

	public Personnage(String nom, Univers univers) {
		this.nom = nom;
		this.univers = univers;
		this.biographie = new Biographie();
	}

	public void ajouterEpisode(Episode episode) {
		biographie.ajouterEpisode(episode);
	}

	public void revelerSecret(Paragraphe paragraphe) {
		paragraphe.setEstSecret(false);
	}

	// Getters / Setters
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
	public Date getDateNaissance() { return dateNaissance; }
	public void setDateNaissance(Date dateNaissance) { this.dateNaissance = dateNaissance; }
	public String getProfession() { return profession; }
	public void setProfession(String profession) { this.profession = profession; }
	public Univers getUnivers() { return univers; }
	public void setUnivers(Univers univers) { this.univers = univers; }
	public Biographie getBiographie() { return biographie; }
	public Utilisateur getProprietaire() { return proprietaire; }
	public void setProprietaire(Utilisateur proprietaire) { this.proprietaire = proprietaire; }
	public Utilisateur getMj() { return mj; }
	public void setMj(Utilisateur mj) { this.mj = mj; }
}
