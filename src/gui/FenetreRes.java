package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import comparaison.Offre;
import comparaison.Resultat;

public class FenetreRes extends JFrame{
	
	public FenetreRes(String titre,Resultat res) {
		setTitle(titre);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width;
		int hauteur = dim.height;
		//setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
		setBounds(largeur/2-215,hauteur/2-170,430,340);
		//setResizable(false);
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		ArrayList<Offre> r = res.getMeilleurs(5);
		for ( Offre o : r)
			main.add(new PanneauTrain(o.getDepart(),o.getArrivee()));
		
		this.add(main);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}