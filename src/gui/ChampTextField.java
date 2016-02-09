package gui;

import javax.swing.JTextField;

public class ChampTextField extends Champ<JTextField>{

	public ChampTextField(String labelText) {
		super(labelText, new JTextField());
	}
	
	public String getText(){
		return champ.getText();
	}
	public void setText(String s){
		champ.setText(s);
	}

}
