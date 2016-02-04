package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import comparaison.Offre;
import comparaison.Resultat;
import elements.Horaire;

public class FenetreRes extends JFrame{
	
	public FenetreRes(String titre,Resultat res) {
		setTitle(titre);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width;
		int hauteur = dim.height;
		//setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
		setBounds(largeur/2-425,hauteur/2-200,850,400);
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