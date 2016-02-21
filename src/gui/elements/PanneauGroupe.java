package gui.elements;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Panneau de groupement, affiche un bord et un titre
 */
@SuppressWarnings("serial")
public class PanneauGroupe extends JPanel{
	public PanneauGroupe(String titre){
		this.setBorder(BorderFactory.createTitledBorder(titre));
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
	}
}
