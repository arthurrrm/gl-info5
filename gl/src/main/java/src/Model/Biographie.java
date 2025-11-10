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

	public List<Episode> getEpisodesChronologiques() {
		// Retourne copie non triée (chronologie à définir plus tard)
		return Collections.unmodifiableList(episodes);
	}

	public List<Episode> getEpisodes() { return episodes; }
}
