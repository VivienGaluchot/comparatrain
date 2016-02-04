package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comparaison.Offre;

@SuppressWarnings("serial")
public class PanneauTrain extends JPanel{
	Ligne l;
	
	public PanneauTrain (Offre o){
		JLabel hd = new JLabel(o.getDepart().horaire.toString());
		l = new Ligne(o);
		JLabel ha = new JLabel(o.getArrivee().horaire.getHeure()+" ");
		JButton reserver = new JButton("Réserver");
		reserver.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}});
		JButton info = new JButton("Info");
		info.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e) {
				
			}});
		add(hd);
		add(l);
		add(ha);
		add(reserver);
		add(info);
	}
}
