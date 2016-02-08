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
import gui.ListPanel;
import gui.MyJFrame;
import gui.SpinnerChamp;
import train.Train;

@SuppressWarnings("serial")
public class EditTrainFrame extends MyJFrame{	
	Train train;
	boolean nouveau;
	ListPanel<Train> father;
	
	private GareComboBox comboBoxD;
	private GareComboBox comboBoxA;

	private ChampHoraire champHoraireD;
	private ChampHoraire champHoraireA;
	
	private ArrayList<Escale> escales;
	ListPanel<Escale> listeEscale;
	
	private JButton valider;
	
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
			SpinnerChamp id = new SpinnerChamp("Id : ",9999);
			box.add(id);
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
			valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	            	try {
	            		train.setId((Integer) id.getValue());
	            		GareHoraire depart = new GareHoraire(comboBoxD.getSelectedItem(),champHoraireD.getHoraire());
	            		train.setDepart(depart);
	            		GareHoraire arrivee = new GareHoraire(comboBoxA.getSelectedItem(),champHoraireA.getHoraire());
	            		train.setArrivee(arrivee);
	            		train.setEscales(escales);
	            		if(nouveau)
	            			Donnees.getInstance().addTrain(train);
		            	father.majList();
		            	setVisible(false);
					} catch (Erreur e1) {
						comboBoxD.setWrong(true);
						comboBoxA.setWrong(true);

						champHoraireD.setWrong(true);
						champHoraireA.setWrong(true);
						System.out.println(e1);
					}
	            }
	        });
			box.add(valider);
		main.add(box);
		
		// Mise a jour des champs
		if(train.getId() != null)
			id.setValue(train.getId());
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