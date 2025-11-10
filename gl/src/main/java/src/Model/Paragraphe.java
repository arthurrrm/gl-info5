package src.Model;

/**
 * Paragraphe (potentiellement secret) d'un épisode.
 */
public class Paragraphe {
	private String texte;
	private boolean estSecret;

	public Paragraphe(String texte, boolean estSecret) {
		this.texte = texte;
		this.estSecret = estSecret;
	}

	public String getTexte() { return texte; }
	public void setTexte(String texte) { this.texte = texte; }
	public boolean isEstSecret() { return estSecret; }
	public void setEstSecret(boolean estSecret) { this.estSecret = estSecret; }
}
