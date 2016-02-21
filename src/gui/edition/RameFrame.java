package gui.edition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.elements.MyJFrame;
import gui.elements.SpinnerChamp;

import donnee.Donnees;
import elements.Erreur;
import train.Rame;
import train.Wagon;

@SuppressWarnings("serial")
public class RameFrame extends MyJFrame{	
	Rame rame;
	boolean nouveau;
	PanneauListe<Rame> father;
	
	private SpinnerChamp id;
	
	private ArrayList<Wagon> wagons;
	PanneauListe<Wagon> listeWagons;
	
	public RameFrame(Rame r, PanneauListe<Rame> f){
		if(r == null){
			rame = new Rame();
			rame.link();
			nouveau = true;
		}
		else{
			rame = r;
			nouveau = false;
		}
		
		father = f;
		
		setTitle("Edition");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		JPanel box = new JPanel();
			id = new SpinnerChamp("Id : ",9999);
			box.add(id);
		main.add(box);
		
		wagons = rame.getWagons();
		
		listeWagons = new PanneauListe<Wagon>("Wagons",Wagon.class, wagons);
		main.add(listeWagons);
		
		
		box = new JPanel();
			JButton annuler = new JButton("Annuler");
			annuler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
	        	setVisible(false);
	        }});
			box.add(annuler);
			JButton valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
            	try {
            		Donnees.rames.changeId(rame, id.getValue());
            		
            		if(nouveau)
            			Donnees.rames.add(rame);
        				rame.link();	
	            	father.majList();
	            	setVisible(false);
				} catch (Erreur e1) {
					if(e1.getType() == Erreur.EXISTE)
						id.setWrong(true);
					else{
						//	
					}
					System.out.println(e1);
				}
	        }});
			box.add(valider);
		main.add(box);
		
		// Mise a jour des champs
		if(rame.getId() != null)
			id.setValue(rame.getId());
		else
			id.setValue(Donnees.rames.getFreeId());		
		
		add(main);
		
		positionner();
	}
}