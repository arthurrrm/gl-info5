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

	public Episode(String titre) {
		this.titre = titre;
	}

	public void ajouterParagraphe(Paragraphe p) {
		contenu.add(p);
	}

	public List<Paragraphe> getContenu() { return contenu; }
	public StatusEpisode getStatut() { return statut; }
	public void setStatut(StatusEpisode statut) { this.statut = statut; }
}
