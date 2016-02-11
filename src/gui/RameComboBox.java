package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import donnee.Donnees;
import train.Rame;

@SuppressWarnings("serial")
public class RameComboBox extends Champ<JComboBox<Rame>>{
	Rame[] rames;

	public RameComboBox(String labelText) {
		super(labelText, new JComboBox<Rame>());
		champ.setBackground(Color.white);
		champ.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));;
		update();
	}
	
	public void setSelectedRame(Rame r){
		this.champ.setSelectedItem(r);
	}
	
	public void update(){
		rames = Donnees.getRamesT();
		champ.setModel(new DefaultComboBoxModel<Rame>(rames));
	}
	
	public Rame getSelectedItem(){
		return (Rame) champ.getSelectedItem();
	}
}