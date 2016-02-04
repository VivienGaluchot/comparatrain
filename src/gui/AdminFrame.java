package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import donnée.Donnees;

/**
 * liste des train
 * lien vers l'ajout d'un train
 * @author g12079772
 *
 */
@SuppressWarnings("serial")
public class AdminFrame<E> extends JFrame{
	
	public AdminFrame(String titre, List<E> list, Donnees data){
		
		setTitle(titre);
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width;
		int hauteur = dim.height;
		setBounds(largeur/2-300,hauteur/2-200,600,400);
		
		DefaultListModel<E> listeM = new DefaultListModel<E>();
		
		for(E t : list)
			listeM.addElement(t);
		
		JList<E> jlist = new JList<E>(listeM);
		JScrollPane scrollPane = new JScrollPane(jlist);
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		main.add(scrollPane);
		
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
	            	// A FAIRE
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
