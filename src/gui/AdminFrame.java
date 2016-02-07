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

import donnee.Donnees;
import elements.Indexable;
import elements.Ville;
import train.Train;
import utilisateur.Client;
import elements.Gare;

/**
 * liste des train
 * lien vers l'ajout d'un train
 * @author g12079772
 *
 */
@SuppressWarnings("serial")
public class AdminFrame<E extends Indexable> extends MyJFrame{
	final Class<E> typeClass;

	List<E> elements;
	
	DefaultListModel<E> listeM;
	JList<E> list;
	
	public AdminFrame(String titre, Class<E> paramClass, List<E> elements){		
		setTitle(titre);
		typeClass = paramClass;
		this.elements = elements;
		
		listeM = new DefaultListModel<E>();
		list = new JList<E>(listeM);
		
		majList();
		
		JScrollPane scrollPane = new JScrollPane(list);
		
		GroupPanel main = new GroupPanel("Liste");
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		main.add(scrollPane);
		
		JPanel box1 = new JPanel();
			JButton nouveau = new JButton("Nouveau");
			nouveau.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
				if(typeClass == Train.class){
	            	EditTrainFrame ajoutTrain = new EditTrainFrame(null);
	            	ajoutTrain.setVisible(true);
				}
				else if(typeClass == Gare.class){
					
				}
				else if(typeClass == Ville.class){
					
				}
				else if(typeClass == Client.class){
					
				}
	        }});
			box1.add(nouveau);
			
			JButton editer = new JButton("Editer");
			editer.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
				if(list.getSelectedIndex() >= 0){
					if(typeClass == Train.class){
						Train train = (Train) listeM.getElementAt(list.getSelectedIndex());
		            	EditTrainFrame editTrain = new EditTrainFrame(train);
		            	editTrain.setVisible(true);
					}
					else if(typeClass == Gare.class){
						
					}
					else if(typeClass == Ville.class){
						
					}
					else if(typeClass == Client.class){
						
					}
				}
	        }});
			box1.add(editer);
			
			JButton supprimer = new JButton("Supprimer");
			supprimer.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
				if(typeClass == Train.class){
	            	Train train = (Train) listeM.getElementAt(list.getSelectedIndex());
	            	Donnees.getInstance().removeTrain(train);
				}
				else if(typeClass == Gare.class){
					Gare gare = (Gare) listeM.getElementAt(list.getSelectedIndex());
	            	Donnees.getInstance().removeGare(gare);
				}
				else if(typeClass == Ville.class){
					Ville ville = (Ville) listeM.getElementAt(list.getSelectedIndex());
	            	Donnees.getInstance().removeVille(ville);
				}
				else if(typeClass == Client.class){
					Client client = (Client) listeM.getElementAt(list.getSelectedIndex());
	            	Donnees.getInstance().removeClient(client);
				}
				majList();
	        }});
			box1.add(supprimer);
		main.add(box1);
		
		this.add(main);
		
		positionner();
	}
	
	public void majList(){
		listeM.clear();
		for(E t : elements)
			listeM.addElement(t);	
	}
}
