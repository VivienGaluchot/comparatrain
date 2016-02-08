package gui;

import javax.swing.JFormattedTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

@SuppressWarnings("serial")
public class SpinnerChamp extends Champ<JSpinner>{
	
	public SpinnerChamp(String labelText, int max) {
		super(labelText, new JSpinner());
		
		SpinnerModel model = new SpinnerNumberModel(1,1,max,1);
		champ.setModel(model);
		
		JFormattedTextField txt = ((JSpinner.NumberEditor) champ.getEditor()).getTextField();
		((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
	}
	
	public Integer getValue(){
		return (Integer) champ.getValue();
	}
	
	public void setValue(int value){
		champ.setValue(value);
	}
}
