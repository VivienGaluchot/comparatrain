package gui;

import java.awt.Dimension;

import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ChampTextField extends Champ<JTextField>{

	public ChampTextField(String labelText) {
		super(labelText, new JTextField());
		champ.setPreferredSize( new Dimension( 150, 24 ) );
	}
	
	public String getText(){
		return champ.getText();
	}
	public void setText(String s){
		champ.setText(s);
	}

}
