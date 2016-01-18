package gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public abstract class Champ<E extends Component> extends JPanel{
	JLabel label;
	E champ;
	
	public Champ(String labelText, E champ){		
		if(labelText.length() > 0){
			label = new JLabel(labelText);		
			add(label);
		}
		
		this.champ = champ;
		add(champ);
	}
	
	public void setWrong(boolean b){
		if(b)
			champ.setForeground(Color.RED);
		else
			champ.setForeground(Color.BLACK);
	}
}
