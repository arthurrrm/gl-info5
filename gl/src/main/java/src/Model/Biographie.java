package src.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Biographie composée d'épisodes.
 */
public class Biographie {
	private final List<Episode> episodes = new ArrayList<Episode>();

	public void ajouterEpisode(Episode episode) {
		episodes.add(episode);
	}

	public void supprimerEpisode(Episode episode) {
		episodes.remove(episode);
	}

	public List<Episode> getEpisodesChronologiques() {
		// Retourne copie non triée (chronologie à définir plus tard)
		List<Episode> sorted = new ArrayList<>(episodes);
		Collections.sort(sorted, (e1, e2) -> e1.getDateCreation().compareTo(e2.getDateCreation()));
		return Collections.unmodifiableList(sorted);
	}

	public List<Episode> getEpisodes() {
		return episodes;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Episode ep : episodes) {
			sb.append("Episode: ").append(ep.getStatut()).append("\n");
			for (Paragraphe p : ep.getContenu()) {
				sb.append(" - Paragraphe: ").append(p.getTexte()).append("\n");
			}
		}
		return sb.toString();
	}
}
