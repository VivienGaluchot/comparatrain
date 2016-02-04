package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import donnée.Donnees;
import elements.Gare;

@SuppressWarnings("serial")
public class GareComboBox extends Champ<JComboBox<Gare>>{
	Gare[] gares;

	public GareComboBox(String labelText) {
		super(labelText, new JComboBox<Gare>());
		champ.setBackground(Color.white);
		champ.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));;
		update();
	}
	
	public void setSelectedGare(Gare g){
		this.champ.setSelectedItem(g);
	}
	
	public void update(){
		gares = Donnees.getInstance().getGaresAlph();
		champ.setModel(new DefaultComboBoxModel<Gare>(gares));
	}
	
	public Gare getSelectedItem(){
		return (Gare) champ.getSelectedItem();
	}
}
