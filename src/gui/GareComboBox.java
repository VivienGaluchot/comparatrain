package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import comparaison.Comparateur;
import modele.Gare;

@SuppressWarnings("serial")
public class GareComboBox extends Champ<JComboBox<Gare>>{
	Gare[] gares;
	Comparateur comparateur;

	public GareComboBox(String labelText, Comparateur comparateur) {
		super(labelText, new JComboBox<Gare>());
		this.comparateur = comparateur;
		champ.setBackground(Color.white);
		champ.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));;
		update();
	}
	
	public void update(){
		gares = comparateur.getData().getGaresAlph();
		champ.setModel(new DefaultComboBoxModel<Gare>(gares));
	}
	
	public Gare getSelectedItem(){
		return (Gare) champ.getSelectedItem();
	}
}
