package gui.edition;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.elements.PanneauGroupe;

import donnee.Donnees;
import elements.Ville;
import offre.Billet;
import train.Rame;
import train.Train;
import train.Wagon;
import utilisateur.Client;
import elements.Escale;
import elements.Gare;
import elements.Indexable;

/**
 * liste des train
 * lien vers l'ajout d'un train
 * @author g12079772
 *
 */
@SuppressWarnings("serial")
public class PanneauListe<E> extends PanneauGroupe{
	final Class<E> typeClass;
	
	List<E> elements;
	
	DefaultListModel<E> listeM;
	JList<E> list;
	
	public PanneauListe(String nom, Class<E> paramClass, final List<E> elements){
		super(nom);
		setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		typeClass = paramClass;
		if(elements == null)
			this.elements = new ArrayList<E>();
		else 
			this.elements = elements;
		
		final PanneauListe<E> thisElement = this;
		
		listeM = new DefaultListModel<E>();
		list = new JList<E>(listeM);
		if(Indexable.class.isAssignableFrom(paramClass))
			list.setCellRenderer(new MyListCellRenderer());
		majList();
		
		JScrollPane scrollPane = new JScrollPane(list);		
		add(scrollPane);
		
		JPanel box1 = new JPanel();
			JButton nouveau = new JButton("Nouveau");
			nouveau.addActionListener(new ActionListener(){@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if(typeClass == Train.class){
					TrainFrame ajoutTrain = new TrainFrame(null,(PanneauListe<Train>) thisElement);
	            	ajoutTrain.setVisible(true);
				}
				else if(typeClass == Gare.class){
					GareFrame ajoutGare = new GareFrame(null,(PanneauListe<Gare>) thisElement);
					ajoutGare.setVisible(true);
				}
				else if(typeClass == Ville.class){
					VilleFrame ajoutVille = new VilleFrame(null,(PanneauListe<Ville>) thisElement);
					ajoutVille.setVisible(true);
				}
				else if(typeClass == Client.class){
					ClientFrame ajoutClient = new ClientFrame(null,(PanneauListe<Client>) thisElement);
					ajoutClient.setVisible(true);
				}
				else if(typeClass == Escale.class){
					EscaleFrame ajoutEscale = new EscaleFrame(null,(PanneauListe<Escale>) thisElement);
					ajoutEscale.setVisible(true);
				}
				else if(typeClass == Billet.class){
					BilletFrame ajoutBillet  = new BilletFrame(null,(PanneauListe<Billet>) thisElement);
					ajoutBillet.setVisible(true);
				}
				else if(typeClass == Rame.class){
					RameFrame ajoutRame  = new RameFrame(null,(PanneauListe<Rame>) thisElement);
					ajoutRame.setVisible(true);
				}
				else if(typeClass == Wagon.class){
					WagonFrame ajoutWagon  = new WagonFrame(null,(PanneauListe<Wagon>) thisElement);
					ajoutWagon.setVisible(true);
				}
	        }});
			box1.add(nouveau);
			
			JButton editer = new JButton("Editer");
			editer.addActionListener(new ActionListener(){@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e){
				if(list.getSelectedIndex() >= 0){
					if(typeClass == Train.class){
						Train train = (Train) listeM.getElementAt(list.getSelectedIndex());
		            	TrainFrame editTrain = new TrainFrame(train,(PanneauListe<Train>) thisElement);
		            	editTrain.setVisible(true);
					}
					else if(typeClass == Gare.class){
						Gare gare = (Gare) listeM.getElementAt(list.getSelectedIndex());
						GareFrame editGare = new GareFrame(gare,(PanneauListe<Gare>) thisElement);
		            	editGare.setVisible(true);						
					}
					else if(typeClass == Ville.class){
						Ville ville = (Ville) listeM.getElementAt(list.getSelectedIndex());
						VilleFrame editVille = new VilleFrame(ville,(PanneauListe<Ville>) thisElement);
						editVille.setVisible(true);
					}
					else if(typeClass == Client.class){
						Client client = (Client) listeM.getElementAt(list.getSelectedIndex());
						ClientFrame editClient = new ClientFrame(client,(PanneauListe<Client>) thisElement);
		            	editClient.setVisible(true);	
					}
					else if(typeClass == Escale.class){
						Escale escale = (Escale) listeM.getElementAt(list.getSelectedIndex());
						EscaleFrame editEscale  = new EscaleFrame(escale,(PanneauListe<Escale>) thisElement);
						editEscale.setVisible(true);	
					}
					else if(typeClass == Billet.class){
						Billet billet = (Billet) listeM.getElementAt(list.getSelectedIndex());
						BilletFrame editBillet  = new BilletFrame(billet,(PanneauListe<Billet>) thisElement);
						editBillet.setVisible(true);
					}
					else if(typeClass == Rame.class){
						Rame rame = (Rame) listeM.getElementAt(list.getSelectedIndex());
						RameFrame editRame  = new RameFrame(rame,(PanneauListe<Rame>) thisElement);
						editRame.setVisible(true);
					}
					else if(typeClass == Wagon.class){
						Wagon wagon = (Wagon) listeM.getElementAt(list.getSelectedIndex());
						WagonFrame editWagon  = new WagonFrame(wagon,(PanneauListe<Wagon>) thisElement);
						editWagon.setVisible(true);
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
	            	gare.getVille().removeFromGares(gare);
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
				else if(typeClass == Billet.class){
					Billet billet = (Billet) listeM.getElementAt(list.getSelectedIndex());
					Donnees.billets.remove(billet);
				}
				else if(typeClass == Rame.class){
					Rame rame = (Rame) listeM.getElementAt(list.getSelectedIndex());
					Donnees.rames.remove(rame);
				}
				else if(typeClass == Escale.class){
					Escale escale = (Escale) listeM.getElementAt(list.getSelectedIndex());
					elements.remove(escale);
				}
				else if(typeClass == Wagon.class){
					Wagon wagon = (Wagon) listeM.getElementAt(list.getSelectedIndex());
					elements.remove(wagon);
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
	
	// Extend DefaultListCellRenderer, takes care of most of the work for you
	public class MyListCellRenderer extends DefaultListCellRenderer	{
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus)
		{
			JLabel id = new JLabel(((Indexable) value).getId() + " ");
			JLabel label = (JLabel)super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			label.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));
			JPanel main = new JPanel();
			main.setBackground(Color.WHITE);
			main.setLayout(new BoxLayout(main,BoxLayout.LINE_AXIS));
			main.add(id);
			main.add(label);
			return main;
		}
	}
}
