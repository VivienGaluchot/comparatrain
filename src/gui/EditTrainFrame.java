package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import defaut.Erreur;
import donnee.Donnees;
import elements.Escale;
import elements.Horaire;
import elements.GareHoraire;
import train.Train;

@SuppressWarnings("serial")
public class EditTrainFrame extends MyJFrame{
	
	Train train;
	AdminFrame<Train> father;
	
	private GareComboBox comboBoxD;
	private GareComboBox comboBoxA;
	private GareComboBox comboBoxE;

	private ChampHoraire champHoraire1;
	private ChampHoraire champHoraire2;
	private ChampHoraire champHoraire3;
	private ChampHoraire champHoraire4;
	
	private JButton ajouterE;
	private JButton ajouter;
	
	private ArrayList<Escale> escales = new ArrayList<Escale>();
	
	public EditTrainFrame(Train t, AdminFrame<Train> f){
		if(t == null)
			this.train = new Train();
		else
			this.train = t;
		
		father = f;
		
		setTitle("Edition");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		JPanel box = new GroupPanel("Départ");
			comboBoxD = new GareComboBox("Gare de départ : ");
			box.add(comboBoxD);
			champHoraire1 = new ChampHoraire("Horaire : ");
			box.add(champHoraire1);
		main.add(box);
		
		box = new GroupPanel("Escales");
			JPanel box20 = new JPanel();
				DefaultListModel<Escale> listeM = new DefaultListModel<Escale>();
				JList<Escale> liste = new JList<Escale>(listeM);
				JScrollPane scrollPane = new JScrollPane(liste);
				box20.add(scrollPane);
			box.add(box20);
		
			JPanel box22 = new JPanel();
				comboBoxE = new GareComboBox("Gare : ");
				box22.add(comboBoxE);
				ajouterE = new JButton("Ajouter!");
				ajouterE.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e)
		            {
		            	Horaire h1 = champHoraire2.getHoraire();
		            	Horaire h2 = champHoraire3.getHoraire();
		            	if(h1.estInit() && h2.estInit()){
							try {
								Escale escale = new Escale(comboBoxE.getSelectedItem(),h1,h2);
								escales.add(escale);
								listeM.addElement(escale);
							} catch (Erreur e1) {
								champHoraire2.setWrong(true);
								champHoraire3.setWrong(true);
								System.out.println(e1);
							}
		            	}
		            }
		        });
				box22.add(ajouterE);
			box.add(box22);
			champHoraire2 = new ChampHoraire("Horaire d'arrivée : ");
			box.add(champHoraire2);
			champHoraire3 = new ChampHoraire("Horaire départ : ");
			box.add(champHoraire3);
		main.add(box);
		
		
		box = new GroupPanel("Arrivée");
			comboBoxA = new GareComboBox("Gare d'arrivée : ");
			box.add(comboBoxA);
			champHoraire4 = new ChampHoraire("Horaires : ");
			box.add(champHoraire4);
		main.add(box);
		
		box = new JPanel();
			ajouter = new JButton("Valider");
			ajouter.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	            	try {
	            		GareHoraire depart = new GareHoraire(comboBoxD.getSelectedItem(),champHoraire1.getHoraire());
	            		train.setDepart(depart);
	            		GareHoraire arrivee = new GareHoraire(comboBoxA.getSelectedItem(),champHoraire4.getHoraire());
	            		train.setArrivee(arrivee);
	            		train.setEscales(escales);
	            		
						Donnees.getInstance().addTrain(train);
		            	father.majList();
		            	setVisible(false);
					} catch (Erreur e1) {
						System.out.println(e1);
					}
	            }
	        });
			box.add(ajouter);
		main.add(box);
		
		
		// Mise a jour des champs
		if(train.getDepart() != null){
			champHoraire1.setHoraire(train.getDepart().horaire);
			comboBoxD.setSelectedGare(train.getDepart().gare);
		}
		if(train.getEscales() != null){
			for(Escale e : train.getEscales())
				listeM.addElement(e);
		}
		if(train.getArrivee() != null){
			champHoraire4.setHoraire(train.getArrivee().horaire);
			comboBoxA.setSelectedGare(train.getArrivee().gare);
		}
		
		add(main);
		
		positionner();
	}
}