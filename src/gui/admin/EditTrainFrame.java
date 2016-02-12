package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import defaut.Erreur;
import donnee.Donnees;
import elements.Escale;
import elements.GareHoraire;
import gui.ChampHoraire;
import gui.GareComboBox;
import gui.GroupPanel;
import gui.MyJFrame;
import gui.RameComboBox;
import gui.SpinnerChamp;
import train.Train;

@SuppressWarnings("serial")
public class EditTrainFrame extends MyJFrame{	
	Train train;
	boolean nouveau;
	ListPanel<Train> father;
	
	private SpinnerChamp id;
	private GareComboBox comboBoxD;
	private GareComboBox comboBoxA;
	private RameComboBox comboBoxR;

	private ChampHoraire champHoraireD;
	private ChampHoraire champHoraireA;
	
	private ArrayList<Escale> escales;
	ListPanel<Escale> listeEscale;
	
	public EditTrainFrame(Train t, ListPanel<Train> f){
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
			comboBoxR = new RameComboBox("Rame : ");
			box.add(comboBoxR);
		main.add(box);
		
		box = new GroupPanel("Départ");
			comboBoxD = new GareComboBox("Gare de départ : ");
			box.add(comboBoxD);
			champHoraireD = new ChampHoraire("Horaire : ");
			box.add(champHoraireD);
		main.add(box);
		
		escales = new ArrayList<Escale>();
		
		listeEscale = new ListPanel<Escale>("Escales",Escale.class, escales);
		main.add(listeEscale);
		
		
		box = new GroupPanel("Arrivée");
			comboBoxA = new GareComboBox("Gare d'arrivée : ");
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
			comboBoxR.setSelectedRame(train.getRame());
		if(train.getDepart() != null){
			champHoraireD.setHoraire(train.getDepart().horaire);
			comboBoxD.setSelectedGare(train.getDepart().gare);
		}
		if(train.getEscales() != null){
			for(Escale e : train.getEscales())
				escales.add(e);
			listeEscale.majList();
		}
		if(train.getArrivee() != null){
			champHoraireA.setHoraire(train.getArrivee().horaire);
			comboBoxA.setSelectedGare(train.getArrivee().gare);
		}
		
		add(main);
		
		positionner();
	}
}