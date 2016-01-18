package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import comparaison.Comparateur;

@SuppressWarnings("serial")
public class MonTextField extends JPanel{
	JLabel label;
	JTextField textField;
	String initText;
	boolean init;
	JPopupMenu popup;
	JMenuItem menuItem;
	JMenuItem i = null;
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
					pop(comp.getData().getEntry(getText()));
				else
					popup.setVisible(false);
			}
			public void insertUpdate(DocumentEvent e){
				if(getText().length()>0 && !init)
					pop(comp.getData().getEntry(getText()));
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
			i = new JMenuItem(s);
			i.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent ev) {
		        	textField.setText(s);
		        	popup.setVisible(false);
		        }
		    });
			popup.add(i);
		}
		popup.show(textField, 0, textField.getHeight());
		textField.requestFocus();
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
