package gui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.elements.PanneauGroupe;
import offre.Offre;
import offre.Resultat;
import gui.elements.MyJFrame;

@SuppressWarnings("serial")
public class FenetreRes extends MyJFrame{
	public FenetreRes(Resultat res) {
		setTitle("Recherche");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
				
		List<Offre> r = res.getMeilleurs(10);
		
		JPanel header = new JPanel();
		header.add(new JLabel(r.size() + " offres correspondants à vos critères"));
		main.add(header);
		
		int i = 1;
		for(Offre o : r){
			Double score = o.getEval()*100;
			PanneauGroupe g = new PanneauGroupe("Billet " + i++ + " (" + score.intValue() + "%)");
			g.add(new PanneauTrain(o,this));
			main.add(g);
		}

		add(main);
		
		positionner();
		afficher();
	}
	
	public void reservationEffectuee(){
		this.setVisible(false);
		JOptionPane.showMessageDialog(this, "Votre réservation a bien été effectuée");
	}
}