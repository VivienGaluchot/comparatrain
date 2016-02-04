package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import donnée.Donnees;
import train.Train;

/**
 * liste des train
 * lien vers l'ajout d'un train
 * @author g12079772
 *
 */
@SuppressWarnings("serial")
public class AdminTrainFrame extends JFrame{
	
	public AdminTrainFrame(Donnees data){
		
		setTitle("Trains");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width;
		int hauteur = dim.height;
		setBounds(largeur/2-300,hauteur/2-200,600,400);
		
		DefaultListModel<Train> listeM = new DefaultListModel<Train>();
		
		for(Train t : data.getTrains())
			listeM.addElement(t);
		
		JList<Train> list = new JList<Train>(listeM);
		JScrollPane scrollPane = new JScrollPane(list);
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		GroupPanel group1 = new GroupPanel("Liste des trains");
		group1.add(scrollPane);
		
		main.add(group1);
		
		JPanel box1 = new JPanel();
			JButton addTrain = new JButton("Nouveau");
			addTrain.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	            	EditTrainFrame ajoutTrain = new EditTrainFrame(data,null);
	            	ajoutTrain.setVisible(true);
	            }
	        });
			box1.add(addTrain);
			
			JButton editTrain = new JButton("Editer");
			editTrain.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	            	Train train = listeM.getElementAt(list.getSelectedIndex());
	            	EditTrainFrame editTrain = new EditTrainFrame(data,train);
	            	editTrain.setVisible(true);
	            }
	        });
			box1.add(editTrain);
			
			JButton delTrain = new JButton("Supprimer");
			delTrain.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	            	// A FAIRE
	            }
	        });
			box1.add(delTrain);
		main.add(box1);
		
		this.add(main);
		pack();
	}
}
