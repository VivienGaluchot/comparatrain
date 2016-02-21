package gui.edition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.elements.ChampComboBox;
import gui.elements.ChampHoraire;
import gui.elements.PanneauGroupe;
import gui.elements.MyJFrame;
import gui.elements.SpinnerChamp;

import donnee.Donnees;
import elements.Erreur;
import elements.Escale;
import elements.Gare;
import elements.GareHoraire;
import train.Rame;
import train.Train;

@SuppressWarnings("serial")
public class TrainFrame extends MyJFrame{	
	Train train;
	boolean nouveau;
	PanneauListe<Train> father;
	
	private SpinnerChamp id;
	private ChampComboBox<Gare> comboBoxD;
	private ChampComboBox<Gare> comboBoxA;
	private ChampComboBox<Rame> comboBoxR;

	private ChampHoraire champHoraireD;
	private ChampHoraire champHoraireA;
	
	private ArrayList<Escale> escales;
	PanneauListe<Escale> listeEscale;
	
	public TrainFrame(Train t, PanneauListe<Train> f){
		if(t == null){
			train = new Train();
			nouveau = true;
		}
		else{
			train = t;
			nouveau = false;
		}
		
		father = f;
		
		setTitle("Edition");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box,BoxLayout.LINE_AXIS));
			id = new SpinnerChamp("Id : ",9999);
			box.add(id);
			comboBoxR = new ChampComboBox<Rame>("Rame : ",Donnees.getRamesT());
			box.add(comboBoxR);
		main.add(box);
		
		box = new PanneauGroupe("Départ");
			comboBoxD = new ChampComboBox<Gare>("Gare de départ : ",Donnees.getGaresAlph());
			box.add(comboBoxD);
			champHoraireD = new ChampHoraire("Horaire : ");
			box.add(champHoraireD);
		main.add(box);
		
		escales = new ArrayList<Escale>();
		
		listeEscale = new PanneauListe<Escale>("Escales",Escale.class, escales);
		main.add(listeEscale);
		
		
		box = new PanneauGroupe("Arrivée");
			comboBoxA = new ChampComboBox<Gare>("Gare d'arrivée : ",Donnees.getGaresAlph());
			box.add(comboBoxA);
			champHoraireA = new ChampHoraire("Horaires : ");
			box.add(champHoraireA);
		main.add(box);
		
		box = new JPanel();
			JButton annuler = new JButton("Annuler");
			annuler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
	        	setVisible(false);
	        }});
			box.add(annuler);
			JButton valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
            	try {
            		Donnees.trains.changeId(train, id.getValue());
            		train.setRame(comboBoxR.getSelectedItem());
            		GareHoraire depart = new GareHoraire(comboBoxD.getSelectedItem(),champHoraireD.getHoraire());
            		train.setDepart(depart);
            		GareHoraire arrivee = new GareHoraire(comboBoxA.getSelectedItem(),champHoraireA.getHoraire());
            		train.setArrivee(arrivee);
            		train.setEscales(escales);
            		if(nouveau)
            			Donnees.trains.add(train);
	            	father.majList();
	            	setVisible(false);
				} catch (Erreur e1) {
					if(e1.getType() == Erreur.EXISTE)
						id.setWrong(true);
					else{
						comboBoxD.setWrong(true);
						comboBoxA.setWrong(true);

						champHoraireD.setWrong(true);
						champHoraireA.setWrong(true);
					}
					System.out.println(e1);
				}
	        }});
			box.add(valider);
		main.add(box);
		
		// Mise a jour des champs
		if(train.getId() != null)
			id.setValue(train.getId());
		else
			id.setValue(Donnees.trains.getFreeId());
		if(train.getRame() != null)
			comboBoxR.setSelectedItem(train.getRame());
		if(train.getDepart() != null){
			champHoraireD.setHoraire(train.getDepart().horaire);
			comboBoxD.setSelectedItem(train.getDepart().gare);
		}
		if(train.getEscales() != null){
			for(Escale e : train.getEscales())
				escales.add(e);
			listeEscale.majList();
		}
		if(train.getArrivee() != null){
			champHoraireA.setHoraire(train.getArrivee().horaire);
			comboBoxA.setSelectedItem(train.getArrivee().gare);
		}
		
		add(main);
		
		positionner();
	}
}