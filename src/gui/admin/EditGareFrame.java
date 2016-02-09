package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import defaut.Erreur;
import donnee.Donnees;
import elements.Gare;
import gui.ChampTextField;
import gui.ListPanel;
import gui.MyJFrame;
import gui.SpinnerChamp;
import gui.VilleComboBox;

@SuppressWarnings("serial")
public class EditGareFrame extends MyJFrame{	
	Gare gare;
	boolean nouveau;
	ListPanel<Gare> father;
	
	private JButton valider;
	
	public EditGareFrame(Gare g, ListPanel<Gare> f){
		if(g == null){
			gare = new Gare();
			nouveau = true;
		}
		else{
			gare = g;
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
		box = new JPanel();
			VilleComboBox ville = new VilleComboBox("Ville : ");
			box.add(ville);
		main.add(box);
		box = new JPanel();
			ChampTextField nom = new ChampTextField("Nom : ");
			box.add(nom);
		main.add(box);
		

		
		box = new JPanel();
			valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e)
	            {
	            	try {
	            		gare.setId((Integer) id.getValue());
	            		if(nouveau)
	            			Donnees.gares.add(gare);
		            	father.majList();
		            	setVisible(false);
					} catch (Erreur e1) {
						System.out.println(e1);
					}
	            }
	        });
			box.add(valider);
		main.add(box);
		
		// Mise a jour des champs
		if(gare.getId() != null)
			id.setValue(gare.getId());
		
		add(main);
		
		positionner();
	}
}