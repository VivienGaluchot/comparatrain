package gui;

import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import elements.Preference;
import gui.elements.PanneauGroupe;
import offre.Offre;
import offre.Resultat;
import gui.elements.MyJFrame;

@SuppressWarnings("serial")
public class FenetreRes extends MyJFrame{
	public FenetreRes(Preference pref, Resultat<Offre> res) {
		setTitle("Recherche");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
				
		List<Offre> r = res.getMeilleurs(10);
		
		JPanel header = new JPanel();
		if(r.size() == 0)
			header.add(new JLabel("Aucune offre ne corresponds à vos critères :"));
		else if(r.size() == 1)
			header.add(new JLabel("Une offre corresponds à vos critères :"));
		else
			header.add(new JLabel(r.size() + " offres correspondent à vos critères :"));
		main.add(header);
		
		JPanel info = new JPanel();
		info.add(new JLabel(pref.toString()));
		main.add(info);
		
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