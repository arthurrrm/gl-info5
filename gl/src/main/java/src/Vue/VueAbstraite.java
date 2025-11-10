package src.Vue;

import javax.swing.*;

/**
 * Base pour les vues concrètes.
 */
public abstract class VueAbstraite extends JFrame implements IVue {
	protected JFrame mainFrame;

	public VueAbstraite() {
		this.mainFrame = this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 400);
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
