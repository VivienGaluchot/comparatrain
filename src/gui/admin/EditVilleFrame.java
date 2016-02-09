package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import defaut.Erreur;
import donnee.Donnees;
import elements.Ville;
import gui.ChampTextField;
import gui.GroupPanel;
import gui.ListPanel;
import gui.MyJFrame;
import gui.SpinnerChamp;

@SuppressWarnings("serial")
public class EditVilleFrame extends MyJFrame{
	

	Ville ville;
	ListPanel<Ville> father;
	boolean nouveau;
	

	private ChampTextField nom;

	
	private JButton valider;
	
	public EditVilleFrame(Ville v, ListPanel<Ville> f){
		if(v == null){
			ville = new Ville();
			nouveau = true;
		}
		else{
			ville = v;
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
		
		box = new GroupPanel("Ville");
		nom = new ChampTextField("Nom");
		nom.setText(ville.getNom());
		box.add(nom);
		main.add(box);
			
		box = new JPanel();
		valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	try {
            		ville.setId((Integer) id.getValue());

            		ville.setNom(nom.getText());

            		if(nouveau)
            			Donnees.villes.add(ville);
	            	father.majList();
	            	setVisible(false);
				} catch (Erreur e1) {
					nom.setWrong(true);
					System.out.println(e1);
				}
            }
        });
		box.add(valider);
	main.add(box);
	
	add(main);
	
	positionner();
	}
}
