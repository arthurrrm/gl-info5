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
	private Paragraphe situationInitiale;
	private String lieu;
	private String dateStr;

	public Partie(String titre, Univers univers, Utilisateur maitreJeu, Paragraphe situationInitiale,
			String lieu, String dateStr) {
		this.titre = titre;
		this.univers = univers;
		this.maitreJeu = maitreJeu;
		this.situationInitiale = situationInitiale;
		this.lieu = lieu;
		this.dateStr = dateStr;
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

	public Utilisateur getMaitreJeu() {
		return maitreJeu;
	}

	public Paragraphe getSituationInitiale() {
		return situationInitiale;
	}

	public String getLieu() {
		return lieu;
	}

	public String getDateStr() {
		return dateStr;
	}
}
