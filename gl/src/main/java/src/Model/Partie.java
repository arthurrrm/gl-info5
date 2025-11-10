package src.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Entité domaine Partie.
 */
public class Partie {
	private String titre;
	private Univers univers;
	private final List<Personnage> participants = new ArrayList<Personnage>();
	private StatusPartie statut = StatusPartie.PROPOSEE;
	private String resumeFinal;
	private Utilisateur maitreJeu;

	public Partie(String titre, Univers univers, Utilisateur maitreJeu) {
		this.titre = titre;
		this.univers = univers;
		this.maitreJeu = maitreJeu;
	}

	public void ajouterParticipant(Personnage perso) {
		if (!participants.contains(perso) && perso.getUnivers() == this.univers) {
			participants.add(perso);
		}
	}

	public void finaliser(String resume) {
		this.resumeFinal = resume;
		this.statut = StatusPartie.TERMINEE;
	}

	// Getters / Setters
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Univers getUnivers() {
		return univers;
	}

	public void setUnivers(Univers univers) {
		this.univers = univers;
	}

	public List<Personnage> getParticipants() {
		return participants;
	}

	public StatusPartie getStatut() {
		return statut;
	}

	public void setStatut(StatusPartie statut) {
		this.statut = statut;
	}

	public String getResumeFinal() {
		return resumeFinal;
	}
}
