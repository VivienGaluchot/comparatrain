package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import defaut.Erreur;
import donnee.Donnees;
import elements.Gare;
import gui.ChampTextField;
import gui.MyJFrame;
import gui.SpinnerChamp;
import gui.VilleComboBox;
import train.Wagon;

@SuppressWarnings("serial")
public class EditWagonFrame extends MyJFrame{	
	Wagon wagon;
	boolean nouveau;
	ListPanel<Wagon> father;
	
	private SpinnerChamp id;
	
	public EditWagonFrame(Wagon w, ListPanel<Wagon> f){
		
		
		father = f;
		
		setTitle("Edition");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		JPanel box = new JPanel();
			id = new SpinnerChamp("Id : ",9999);
			box.add(id);
		main.add(box);
		box = new JPanel();
			String[] st = {"1er Classe","2ieme Classe","Wagon Bar"};
			JComboBox ComboBoxWagon = new JComboBox(st);
			box.add(ComboBoxWagon);
		main.add(box);
		
		if(w == null){
			wagon = new Wagon(id.getValue(),ComboBoxWagon.getSelectedIndex()+1);
			nouveau = true;
		}
		else{
			wagon = w;
			nouveau = false;
		}
		
		box = new JPanel();
			JButton annuler = new JButton("Annuler");
			annuler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
	        	setVisible(false);
	        }});
			box.add(annuler);
			JButton valider = new JButton("Valider");
			valider.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
            	wagon.setId(id.getValue());
				if(nouveau)
					father.addElement(wagon);
					
				father.majList();
				setVisible(false);
	        }});
			box.add(valider);
		main.add(box);
		
		// Mise a jour des champs
		if(wagon.getId() != null)
			id.setValue(wagon.getId());
		
		add(main);
		
		positionner();
	}
}