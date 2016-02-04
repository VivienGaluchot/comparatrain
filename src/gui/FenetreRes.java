package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
		setBounds(largeur/2-430,hauteur/2-200,860,400);
		//setResizable(false);
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));

		List<Offre> r = res.getMeilleurs(10);

		for ( Offre o : r){

			main.add(new PanneauTrain(o));
		}
		
		JScrollPane listScroller = new JScrollPane(main);
		listScroller.setPreferredSize(new Dimension(250, 80));
		add(listScroller);
	}
}