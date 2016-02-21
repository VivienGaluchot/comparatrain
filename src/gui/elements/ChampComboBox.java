package gui.elements;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Menu déroulant générique
 */
@SuppressWarnings("serial")
public class ChampComboBox<E> extends Champ<JComboBox<E>>{
	E[] elements;

	public ChampComboBox(String labelText, E[] elements) {
		super(labelText, new JComboBox<E>());
		champ.setBackground(Color.white);
		champ.setFont(new javax.swing.plaf.FontUIResource("Arial",Font.PLAIN,12));
		update(elements);
	}
	
	public void setSelectedItem(E e){
		this.champ.setSelectedItem(e);
	}
	
	public void update(E[] elements){
		this.elements = elements;
		champ.setModel(new DefaultComboBoxModel<E>(elements));
	}
	
	@SuppressWarnings("unchecked")
	public E getSelectedItem(){
		return (E) champ.getSelectedItem();
	}
}
