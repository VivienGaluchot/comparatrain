package gui.edition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.elements.ChampTextField;
import gui.elements.PanneauGroupe;
import gui.elements.MyJFrame;
import gui.elements.SpinnerChamp;

import donnee.Donnees;
import elements.Erreur;
import elements.Ville;

@SuppressWarnings("serial")
public class VilleFrame extends MyJFrame{
	private Ville ville;
	private PanneauListe<Ville> father;
	private boolean nouveau;
	
	private SpinnerChamp id;
	private ChampTextField nom;
	
	public VilleFrame(Ville v, PanneauListe<Ville> f){
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
			id = new SpinnerChamp("Id : ",9999);
			if(ville.getId() != null)
				id.setValue(ville.getId());
			else
				id.setValue(Donnees.villes.getFreeId());
			box.add(id);
		main.add(box);
		
		box = new PanneauGroupe("Ville");
		nom = new ChampTextField("Nom");
		nom.setText(ville.getNom());
		box.add(nom);
		main.add(box);
			
		box = new JPanel();
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
        	setVisible(false);
        }});
		box.add(annuler);
		JButton valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
        	try {
        		Donnees.villes.changeId(ville, id.getValue());
        		ville.setNom(nom.getText());
        		if(nouveau)
        			Donnees.villes.add(ville);
            	father.majList();
            	setVisible(false);
			} catch (Erreur e1) {
				if(e1.getType() == Erreur.EXISTE)
					id.setWrong(true);
				else
					nom.setWrong(true);
				System.out.println(e1);
			}
        }});
		box.add(valider);
	main.add(box);
	
	add(main);
	
	positionner();
	}
}
