package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * liste des train
 * lien vers l'ajout d'un train
 * @author g12079772
 *
 */
@SuppressWarnings("serial")
public class AdminFrame<E> extends MyJFrame{
	
	public AdminFrame(String titre, List<E> list){
		
		setTitle(titre);
		
		DefaultListModel<E> listeM = new DefaultListModel<E>();
		
		for(E t : list)
			listeM.addElement(t);
		
		JList<E> jlist = new JList<E>(listeM);
		JScrollPane scrollPane = new JScrollPane(jlist);
		
		GroupPanel main = new GroupPanel("Liste");
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		main.add(scrollPane);
		
		JPanel box1 = new JPanel();
			JButton addTrain = new JButton("Nouveau");
			addTrain.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
	            	EditTrainFrame ajoutTrain = new EditTrainFrame(null);
	            	ajoutTrain.setVisible(true);
	        }});
			box1.add(addTrain);
			
			JButton editTrain = new JButton("Editer");
			editTrain.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
	            	// A FAIRE
	        }});
			box1.add(editTrain);
			
			JButton delTrain = new JButton("Supprimer");
			delTrain.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
	            	// A FAIRE
	        }});
			box1.add(delTrain);
		main.add(box1);
		
		this.add(main);
		
		positionner();
	}
}
