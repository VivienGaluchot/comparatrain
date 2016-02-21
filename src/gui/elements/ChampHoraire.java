package gui.elements;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import elements.Erreur;
import elements.Horaire;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Element de GUI permetant d'entrer une Horaire
 */
@SuppressWarnings("serial")
public class ChampHoraire extends Champ<JTextField>{
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
		super(labelText, null);
		
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
		super(null,null);
		
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
	
	public void setHoraire(Horaire h){
		jjmmaaaa.setText(h.getJour());
		hh.setText(h.getHH());
		mm.setText(h.getMm());
	}
	
	public JComboBox<String> getComboBox(){
		if(comboBox == null) return null;
		return comboBox;
	}
}