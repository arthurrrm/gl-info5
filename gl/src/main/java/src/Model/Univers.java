package src.Model;

/**
 * Univers fictionnel d'une partie ou d'un personnage.
 */
public class Univers {
	private String nom;

	public Univers(String nom) { this.nom = nom; }
	public String getNom() { return nom; }
	public void setNom(String nom) { this.nom = nom; }
}
