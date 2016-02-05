package gui;

import javax.swing.JTextArea;

import comparaison.Offre;

@SuppressWarnings("serial")
public class InfoTrain extends MyJFrame{
	Offre train;
	
	public InfoTrain(Offre t){
		train = t;
		setTitle("Informations");
		
		JTextArea field = new JTextArea();
		field.setEditable(false);
		field.setBackground(this.getBackground());
		
		field.setText(train.toString());
		
		add(field);
		
		positionner();
		afficher();
	}
}
