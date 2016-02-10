package gui.admin;

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
import elements.Ville;
import gui.GroupPanel;
import train.Train;
import utilisateur.Client;
import elements.Billet;
import elements.Escale;
import elements.Gare;

/**
 * liste des train
 * lien vers l'ajout d'un train
 * @author g12079772
 *
 */
@SuppressWarnings("serial")
public class ListPanel<E> extends GroupPanel{
	final Class<E> typeClass;
	
	List<E> elements;
	
	DefaultListModel<E> listeM;
	JList<E> list;
	
	public ListPanel(String nom, Class<E> paramClass, List<E> elements){
		super(nom);
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		typeClass = paramClass;
		this.elements = elements;		
		ListPanel<E> thisElement = this;
		
		listeM = new DefaultListModel<E>();
		list = new JList<E>(listeM);		
		majList();
		
		JScrollPane scrollPane = new JScrollPane(list);		
		add(scrollPane);
		
		JPanel box1 = new JPanel();
			JButton nouveau = new JButton("Nouveau");
			nouveau.addActionListener(new ActionListener(){@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if(typeClass == Train.class){
					EditTrainFrame ajoutTrain = new EditTrainFrame(null,(ListPanel<Train>) thisElement);
	            	ajoutTrain.setVisible(true);
				}
				else if(typeClass == Gare.class){
					EditGareFrame ajoutGare = new EditGareFrame(null,(ListPanel<Gare>) thisElement);
					ajoutGare.setVisible(true);
				}
				else if(typeClass == Ville.class){
					EditVilleFrame ajoutVille = new EditVilleFrame(null,(ListPanel<Ville>) thisElement);
					ajoutVille.setVisible(true);
				}
				else if(typeClass == Client.class){
					EditClientFrame ajoutClient = new EditClientFrame(null,(ListPanel<Client>) thisElement);
					ajoutClient.setVisible(true);
				}
				else if(typeClass == Escale.class){
					EditEscaleFrame ajoutEscale = new EditEscaleFrame(null,(ListPanel<Escale>) thisElement);
					ajoutEscale.setVisible(true);
				}
	        }});
			box1.add(nouveau);
			
			JButton editer = new JButton("Editer");
			editer.addActionListener(new ActionListener(){@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if(list.getSelectedIndex() >= 0){
					if(typeClass == Train.class){
						Train train = (Train) listeM.getElementAt(list.getSelectedIndex());
		            	EditTrainFrame editTrain = new EditTrainFrame(train,(ListPanel<Train>) thisElement);
		            	editTrain.setVisible(true);
					}
					else if(typeClass == Gare.class){
						Gare gare = (Gare) listeM.getElementAt(list.getSelectedIndex());
						EditGareFrame editGare = new EditGareFrame(gare,(ListPanel<Gare>) thisElement);
		            	editGare.setVisible(true);						
					}
					else if(typeClass == Ville.class){
						Ville ville = (Ville) listeM.getElementAt(list.getSelectedIndex());
						EditVilleFrame editVille = new EditVilleFrame(ville,(ListPanel<Ville>) thisElement);
						editVille.setVisible(true);
					}
					else if(typeClass == Client.class){
						Client client = (Client) listeM.getElementAt(list.getSelectedIndex());
						EditClientFrame editClient = new EditClientFrame(client,(ListPanel<Client>) thisElement);
		            	editClient.setVisible(true);	
					}
					else if(typeClass == Escale.class){
						Escale escale = (Escale) listeM.getElementAt(list.getSelectedIndex());
						EditEscaleFrame editEscale  = new EditEscaleFrame(escale,(ListPanel<Escale>) thisElement);
						editEscale.setVisible(true);	
					}
					else if(typeClass == Billet.class){
						Billet billet = (Billet) listeM.getElementAt(list.getSelectedIndex());
						EditBilletFrame editBillet  = new EditBilletFrame(billet,(ListPanel<Billet>) thisElement);
						editBillet.setVisible(true);
					}
				}
	        }});
			box1.add(editer);
			
			JButton supprimer = new JButton("Supprimer");
			supprimer.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
				if(typeClass == Train.class){
	            	Train train = (Train) listeM.getElementAt(list.getSelectedIndex());
	            	Donnees.trains.remove(train);
				}
				else if(typeClass == Gare.class){
					Gare gare = (Gare) listeM.getElementAt(list.getSelectedIndex());
	            	Donnees.gares.remove(gare);
				}
				else if(typeClass == Ville.class){
					Ville ville = (Ville) listeM.getElementAt(list.getSelectedIndex());
	            	Donnees.villes.remove(ville);
				}
				else if(typeClass == Client.class){
					Client client = (Client) listeM.getElementAt(list.getSelectedIndex());
	            	Donnees.clients.remove(client);
				}
				else if(typeClass == Escale.class){
					Escale escale = (Escale) listeM.getElementAt(list.getSelectedIndex());
					elements.remove(escale);
				}
				else if(typeClass == Billet.class){
					Billet billet = (Billet) listeM.getElementAt(list.getSelectedIndex());
					elements.remove(billet);
				}
				majList();
	        }});
			box1.add(supprimer);
		add(box1);
	}
	
	public void majList(){
		listeM.clear();
		for(E t : elements)
			listeM.addElement(t);
	}
	
	public void addElement(E el){
		elements.add(el);
	}
	
	public DefaultListModel<E> getListModel(){
		return listeM;
	}
}
