package gui.elements;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représente un champ abstrait
 * Un champ est composé d'un label et d'un autre component de GUI
 */
@SuppressWarnings("serial")
public abstract class Champ<E extends Component> extends JPanel{
	public JLabel label;
	public E champ;
	
	public Champ(String labelText, E champ){
		if(labelText != null && labelText.length() > 0){
			label = new JLabel(labelText);
			add(label);
		}
		
		if(champ != null){
			this.champ = champ;
			add(champ);
		}
	}
	
	public void setWrong(boolean b){
		if(b)
			champ.setForeground(Color.RED);
		else
			champ.setForeground(Color.BLACK);
	}
}
