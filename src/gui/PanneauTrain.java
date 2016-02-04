package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comparaison.Offre;

public class PanneauTrain extends JPanel{
	
	Ligne l;
	
	public PanneauTrain (Offre o){
		JLabel hd = new JLabel(o.getDepart().horaire.toString());
		l = new Ligne(o);
		JLabel ha = new JLabel(o.getArrivee().horaire.toString());
		JButton reserver = new JButton("Réserver");
		reserver.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}});
		add(hd);
		add(l);
		add(ha);
		add(reserver);

	
	
	}
}
