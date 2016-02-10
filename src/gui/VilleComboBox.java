package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import donnee.Donnees;
import elements.Ville;

@SuppressWarnings("serial")
public class VilleComboBox extends Champ<JComboBox<Ville>>{
	Ville[] villes;

	public VilleComboBox(String labelText) {
		super(labelText, new JComboBox<Ville>());
		champ.setBackground(Color.white);
		champ.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));
		update();
	}
	
	public void setSelectedVille(Ville v){
		this.champ.setSelectedItem(v);
	}
	
	public void update(){
		villes = Donnees.getVillesAlph();
		champ.setModel(new DefaultComboBoxModel<Ville>(villes));
	}
	
	public Ville getSelectedItem(){
		return (Ville) champ.getSelectedItem();
	}
}
