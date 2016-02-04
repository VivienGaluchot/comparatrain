package gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import defaut.Erreur;
import elements.Escale;
import elements.Gare;
import elements.Horaire;
import train.Train;

@SuppressWarnings("serial")
public class EditTrainFrame extends MyJFrame{
	
	Train train;
	
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
	
	public EditTrainFrame(Train train){
		
		if(train == null){
			this.train = new Train();
		}
		else{
			this.train = train;
		}
		
		setTitle("Edition");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		JPanel box1 = new GroupPanel("Départ");
			comboBoxD = new GareComboBox("Gare de départ : ");
			comboBoxD.setSelectedGare(train.getDepart().gare);
			box1.add(comboBoxD);
			champHoraire1 = new ChampHoraire("Horaire : ");
		box1.add(champHoraire1);
		main.add(box1);
		
		JPanel box2 = new GroupPanel("Escales");
			JPanel box20 = new JPanel();
			DefaultListModel<String> listeM = new DefaultListModel<String>();
			JList<String> liste = new JList<String>(listeM);
			JScrollPane scrollPane = new JScrollPane(liste);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setPreferredSize(new Dimension(500,120));
			box20.add(scrollPane);
		box2.add(box20);
		
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
							escales.add( new Escale((Gare) comboBoxE.getSelectedItem(),h1,h2));
							listeM.addElement(comboBoxE.getSelectedItem().toString()+"   "+ h1.toStringLong() +"   "+ h2.toStringLong() +"\n");
						} catch (Erreur e1) {
							champHoraire2.setWrong(true);
							champHoraire3.setWrong(true);
							System.out.println(e1);
						}
	            	}
	            }
	        });
			box22.add(ajouterE);
		box2.add(box22);
		champHoraire2 = new ChampHoraire("Horaire d'arrivée : ");
		box2.add(champHoraire2);
		champHoraire3 = new ChampHoraire("Horaire départ : ");
		box2.add(champHoraire3);
		
		main.add(box2);
		
		
		JPanel box3 = new GroupPanel("Arrivée");
		comboBoxA = new GareComboBox("Gare d'arrivée : ");
		comboBoxA.setSelectedGare(train.getArrivee().gare);
		box3.add(comboBoxA);
		champHoraire4 = new ChampHoraire("Horaires : ");
		box3.add(champHoraire4);
		main.add(box3);
		
		JPanel box4 = new JPanel();
			
			ajouter = new JButton("Sauvegarder");
			ajouter.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	                // A FAIRE
	            }
	        });
			box4.add(ajouter);
			
		main.add(box4);
		
		add(main);
		
		positionner();
	}
}