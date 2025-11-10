package src.Vue;

import javax.swing.*;
import java.awt.*;
import src.Model.Personnage;
import src.Model.Paragraphe;

/**
 * Vue de consultation de la biographie d'un personnage.
 */
public class VueConsultationBiographie extends VueAbstraite {
	private JTextArea zoneTexteBio = new JTextArea();

	public VueConsultationBiographie() {
		super();
		zoneTexteBio.setEditable(false);
		setLayout(new BorderLayout());
		add(new JScrollPane(zoneTexteBio), BorderLayout.CENTER);
	}

	public void afficherBiographie(Personnage p, boolean estPrivee) {
		StringBuilder sb = new StringBuilder();
		for (Paragraphe par : p.getBiographie().getEpisodes().isEmpty() ? new java.util.ArrayList<Paragraphe>() : p.getBiographie().getEpisodes().get(0).getContenu()) {
			if (!par.isEstSecret() || estPrivee) {
				sb.append(par.getTexte()).append("\n\n");
			}
		}
		zoneTexteBio.setText(sb.toString());
	}
}
