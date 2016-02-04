package gui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import comparaison.Offre;
import comparaison.Resultat;

@SuppressWarnings("serial")
public class FenetreRes extends MyJFrame{
	public FenetreRes(Resultat res) {
		setTitle("Recherche");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
				
		List<Offre> r = res.getMeilleurs(10);

		for ( Offre o : r)
			main.add(new PanneauTrain(o));
		
		JScrollPane listScroller = new JScrollPane(main);
		add(listScroller);
		
		positionner();
		afficher();
	}
}