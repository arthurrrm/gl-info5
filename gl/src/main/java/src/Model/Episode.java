package src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Episode contenant des paragraphes.
 */
public class Episode {
	private final List<Paragraphe> contenu = new ArrayList<Paragraphe>();
	private String titre;
	private StatusEpisode statut = StatusEpisode.BROUILLON;
	private String dateCreation;
	private Partie partieAssociee;

	public Episode(String titre, String dateCreation, Partie partieAssociee) {
		this.titre = titre;
		this.dateCreation = dateCreation;
		this.partieAssociee = partieAssociee;
	}

	public void ajouterParagraphe(Paragraphe p) {
		contenu.add(p);
	}

	public void supprimerParagraphe(Paragraphe p) {
		contenu.remove(p);
	}

	public List<Paragraphe> getContenu() {
		return contenu;
	}

	public StatusEpisode getStatut() {
		return statut;
	}

	public void setStatut(StatusEpisode statut) {
		this.statut = statut;
	}

	public String getTitre() {
		return titre;
	}

	public Partie getPartieAssociee() {
		return partieAssociee;
	}

	public String getDateCreation() {
		return dateCreation;
	}
}
