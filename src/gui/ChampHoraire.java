package gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import defaut.Erreur;
import modele.Horaire;

@SuppressWarnings("serial")
public class ChampHoraire extends JPanel{
	JLabel label;
	JTextField jjmmaaaa;
	JTextField hh;
	JTextField mm;
	
	public ChampHoraire(String labelText){
		if(labelText.length() > 0){
			label = new JLabel(labelText);
			add(label);
		}
		
		jjmmaaaa = new JTextField("jj/mm/aaaa",10);
		jjmmaaaa.setForeground(Color.GRAY);
		ClearOnClick(jjmmaaaa);
		add(jjmmaaaa);
		
		hh = new JTextField("heure",4);
		hh.setForeground(Color.GRAY);
		ClearOnClick(hh);
		add(hh);
		
		mm = new JTextField("min",4);
		mm.setForeground(Color.GRAY);
		ClearOnClick(mm);
		add(mm);
	}
	
	public void setWrong(boolean b){
		if(b){
			jjmmaaaa.setForeground(Color.RED);
			hh.setForeground(Color.RED);
			mm.setForeground(Color.RED);
		}
		else{
			jjmmaaaa.setForeground(Color.BLACK);
			hh.setForeground(Color.BLACK);
			mm.setForeground(Color.BLACK);
		}
	}
	
	public Horaire getHoraire(){
		Horaire h = new Horaire();
		try {
			h.setSerialTime(jjmmaaaa.getText() + " " + hh.getText() + "h" + mm.getText());
			setWrong(false);
		} catch (Erreur e) {
			setWrong(true);
		}
		return h;
	}
	
	public void ClearOnClick(JTextField textField){
		textField.addFocusListener(new FocusListener(){
			boolean init = true;
			String initText = textField.getText();
			
	        public void focusGained(FocusEvent e){
	        	if(init){
		            textField.setText("");
		            init = false;
	        	}
	        	textField.setForeground(Color.BLACK);
	        }
	        
			@Override
			public void focusLost(FocusEvent e) {
				if(textField.getText().length() == 0){
					init = true;
					textField.setForeground(Color.GRAY);
					textField.setText(initText);
				}
			}
	    });
	}
}