package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import elements.Escale;
import gui.ChampHoraire;
import gui.GareComboBox;
import gui.GroupPanel;
import gui.MyJFrame;

@SuppressWarnings("serial")
public class EditEscaleFrame extends MyJFrame{	
	Escale escale;
	boolean nouveau;
	ListPanel<Escale> father;
	
	public EditEscaleFrame(Escale e, ListPanel<Escale> f){
		if(e == null){
			escale = new Escale();
			nouveau = true;
		}
		else{
			escale = e;
			nouveau = false;
		}
		
		father = f;
		
		setTitle("Edition");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		JPanel box = new GroupPanel("Informations");
			final GareComboBox gare = new GareComboBox("Gare : ");
			box.add(gare);
			final ChampHoraire champHoraireA = new ChampHoraire("Arrivée : ");
			box.add(champHoraireA);
			final ChampHoraire champHoraireD = new ChampHoraire("Départ : ");
			box.add(champHoraireD);
		main.add(box);
		
		box = new JPanel();
			JButton annuler = new JButton("Annuler");
			annuler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
	        	setVisible(false);
	        }});
			box.add(annuler);
			JButton valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
        		escale.gare = gare.getSelectedItem();
        		escale.horaireA = champHoraireA.getHoraire();
        		escale.horaireD = champHoraireD.getHoraire();
        		if(escale.estCoherent()){
        			if(nouveau)
        				father.addElement(escale);
                	father.majList();
                	setVisible(false);
        		}
        		else if(escale.horaireA != null && escale.horaireD != null){
        			champHoraireA.setWrong(true);
        			champHoraireD.setWrong(true);
        		}
	        }});
			box.add(valider);
		main.add(box);
		
		// Mise a jour des champs
		if(escale.gare != null)
			gare.setSelectedGare(escale.gare);
		if(escale.horaireA != null)
			champHoraireA.setHoraire(escale.horaireA);
		if(escale.horaireD != null)
			champHoraireD.setHoraire(escale.horaireD);
		
		add(main);
		
		positionner();
	}
}