package gui.edition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.elements.ChampComboBox;
import gui.elements.ChampHoraire;
import gui.elements.PanneauGroupe;
import gui.elements.MyJFrame;

import donnee.Donnees;
import elements.Escale;
import elements.Gare;

@SuppressWarnings("serial")
public class EscaleFrame extends MyJFrame{	
	Escale escale;
	boolean nouveau;
	PanneauListe<Escale> father;
	
	public EscaleFrame(Escale e, PanneauListe<Escale> f){
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
		
		JPanel box = new PanneauGroupe("Informations");
			final ChampComboBox<Gare> gare = new ChampComboBox<Gare>("Gare : ",Donnees.getGaresAlph());
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
			gare.setSelectedItem(escale.gare);
		if(escale.horaireA != null)
			champHoraireA.setHoraire(escale.horaireA);
		if(escale.horaireD != null)
			champHoraireD.setHoraire(escale.horaireD);
		
		add(main);
		
		positionner();
	}
}