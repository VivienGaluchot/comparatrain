package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import comparaison.Comparateur;
import modele.Gare;
import modele.Ville;

@SuppressWarnings("serial")
public class MonTextField extends JPanel{
	JLabel label;
	JTextField textField;
	String initText;
	boolean init;
	JPopupMenu popup;
	JMenuItem menuItem;
	Comparateur comp;
	
	public MonTextField(String labelText, String initText, int lenght, Comparateur comp){
		label = new JLabel(labelText);
		this.add(label);
		this.initText = initText;
		init = true;
		textField = new JTextField(initText,lenght);
		textField.setForeground(Color.GRAY);
		ClearOnClick(textField);
		this.add(textField);
		this.comp = comp;
		
		popup = new JPopupMenu();
		
		textField.getDocument().addDocumentListener(new DocumentListener(){
			public void removeUpdate(DocumentEvent e){
				if(getText().length()>0 && !init)
					pop(getEntry(getText()));
				else
					popup.setVisible(false);
			}
			public void insertUpdate(DocumentEvent e){
				if(getText().length()>0 && !init)
					pop(getEntry(getText()));
				else
					popup.setVisible(false);
			}
			public void changedUpdate(DocumentEvent e){ }
		});
	}
	
	public String getText(){
		return textField.getText();
	}
	
	public void setWrong(boolean b){
		if(b)
			textField.setForeground(Color.RED);
		else
			textField.setForeground(Color.BLACK);
	}
	
	public void pop(String[] list){
		popup.setVisible(false);
		popup.removeAll();
		for(String s : list){
			menuItem = new JMenuItem(s);
			menuItem.setPreferredSize(new Dimension(textField.getWidth(),18));
			menuItem.setFont(textField.getFont());
			menuItem.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent ev) {
		        	textField.setText(s);
		        	popup.setVisible(false);
		        }
		    });
			menuItem.setBackground(Color.white);
			popup.add(menuItem);
		}
		popup.show(textField, 0, textField.getHeight());
		textField.requestFocus();
	}
	
	public String[] getEntry(String str){
		ArrayList<String> list = new ArrayList<String>();
		int i = 0;
		for(Ville v : comp.getData().getVilles()){
			if(i>=5) break;
			if(v.getNom().toLowerCase().contains(str.toLowerCase())){
				list.add(v.getNom());
				i++;
			}
		}
		for(Gare g : comp.getData().getGares()){
			if(i>=5) break;
			if(g.getNom().toLowerCase().contains(str.toLowerCase())){
				list.add(g.getNom());
				i++;
			}
		}
		list.sort(null);
		String [] res = new String[list.size()];
		list.toArray(res);
		return res;
	}
	
	public void ClearOnClick(JTextField textField){
		textField.addFocusListener(new FocusListener(){
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
					popup.setVisible(false);
				}
			}
	    });
	}
}
