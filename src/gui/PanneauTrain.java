package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import defaut.MainActivity;
import donnee.Donnees;
import elements.Erreur;
import offre.Billet;
import offre.Offre;

@SuppressWarnings("serial")
public class PanneauTrain extends JPanel{
	PanneauOffre l;
	
	InfoTrain fen = null;
	
	PanneauTrain luiMeme = this;
	
	public PanneauTrain (final Offre o, final FenetreRes fenetreRes){
		JLabel hd = new JLabel(o.getDepart().horaire.toString());
		l = new PanneauOffre(o);
		JLabel ha = new JLabel(o.getArrivee().horaire.getHeure()+" ");
		JButton reserver = new JButton("Réserver");
		reserver.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) {
			if(MainActivity.current != null){
				for(Billet b : o.getBillets()){
					b.setId(Donnees.billets.getFreeId());
					b.setClient(MainActivity.current);
					try {
						Donnees.billets.add(b);
					} catch (Erreur e1) {
						System.out.println(e1);
					}
				}
				Donnees.sauvegarder();
				fenetreRes.reservationEffectuee();
			}
			else 
				JOptionPane.showMessageDialog(luiMeme, "Veuillez vous connecter");
		}});
		JButton info = new JButton("Info");
		info.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
			if(fen == null)
				fen = new InfoTrain(o);
			fen.afficher();
		}});
		add(hd);
		add(l);
		add(ha);
		add(reserver);
		add(info);
	}
}
