package gui;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import defaut.Erreur;
import elements.Horaire;

@SuppressWarnings("serial")
public class ChampHoraire extends JPanel{
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
			JLabel label = new JLabel(labelText);
			add(label);
		}
		
		jjmmaaaa = new JTextField(dat,10);
		add(jjmmaaaa);
		
		hh = new JTextField(heure,4);
		add(hh);
		
		JLabel labelH = new JLabel("h");
		add(labelH);
		
		mm = new JTextField(min,4);
		add(mm);
	}
	
	public ChampHoraire(String val1, String val2){
		if(val1.length() > 0 && val2.length() >0){
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {val1, val2}));
			add(comboBox);
		}
		
		jjmmaaaa = new JTextField(dat,10);
		add(jjmmaaaa);
		
		hh = new JTextField(heure,4);
		add(hh);
		
		JLabel labelH = new JLabel("h");
		add(labelH);
		
		mm = new JTextField(min,4);
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
		if(comboBox == null) return null;
		return comboBox;
	}
}