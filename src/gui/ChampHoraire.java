package gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import defaut.Erreur;
import modele.Horaire;

@SuppressWarnings("serial")
public class ChampHoraire extends JPanel{
	JLabel label;
	JLabel labelH;
	JComboBox<String> comboBox;
	JTextField jjmmaaaa;
	JTextField hh;
	JTextField mm;
	
	Date actuelle = new Date();
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat heureFormat = new SimpleDateFormat("HH");
	DateFormat minFormat = new SimpleDateFormat("mm");
	String dat = dateFormat.format(actuelle);
	String heure = heureFormat.format(actuelle);
	String min = minFormat.format(actuelle);
	
	public ChampHoraire(String labelText){
		if(labelText.length() > 0){
			label = new JLabel(labelText);
			add(label);
		}
		
		jjmmaaaa = new JTextField(dat,10);
		jjmmaaaa.setForeground(Color.GRAY);
		ClearOnClick(jjmmaaaa);
		add(jjmmaaaa);
		
		hh = new JTextField(heure,4);
		hh.setForeground(Color.GRAY);
		ClearOnClick(hh);
		add(hh);
		
		labelH = new JLabel("h");
		add(labelH);
		
		mm = new JTextField(min,4);
		mm.setForeground(Color.GRAY);
		ClearOnClick(mm);
		add(mm);
	}
	
	public ChampHoraire(String val1,String val2){
		if(val1.length() > 0 && val2.length() >0){
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {val1, val2}));
			add(comboBox);
		}
		
		jjmmaaaa = new JTextField(dat,10);
		jjmmaaaa.setForeground(Color.GRAY);
		ClearOnClick(jjmmaaaa);
		add(jjmmaaaa);
		
		hh = new JTextField(heure,4);
		hh.setForeground(Color.GRAY);
		ClearOnClick(hh);
		add(hh);
		
		labelH = new JLabel("h");
		add(labelH);
		
		mm = new JTextField(min,4);
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
	
	public JComboBox<String> getComboBox(){
		return comboBox;
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