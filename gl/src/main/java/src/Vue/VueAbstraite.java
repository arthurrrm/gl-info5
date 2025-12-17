package src.Vue;

import javax.swing.*;
import java.awt.*;

/**
 * Base pour les vues concrètes.
 */
public abstract class VueAbstraite extends JFrame implements IVue {
	protected JFrame mainFrame;

	public VueAbstraite() {
		this.mainFrame = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000, 800);
		setMinimumSize(new Dimension(1000, 800));
	}

	@Override
	public void afficher() {
		setVisible(true);
	}

	@Override
	public void fermer() {
		dispose();
	}
}
