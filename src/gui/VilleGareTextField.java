package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import donnee.Donnees;
import elements.Gare;
import elements.Ville;

@SuppressWarnings("serial")
public class VilleGareTextField extends Champ<JTextField>{
	String initText;
	boolean init;
	JPopupMenu popup;
	JMenuItem menuItem;
	
	public VilleGareTextField(String labelText, String initText, int lenght){
		super(labelText, new JTextField(initText,lenght));
		
		this.initText = initText;
		init = true;

		champ.setForeground(Color.GRAY);
		ClearOnClick(champ);
		
		popup = new JPopupMenu();
		
		champ.getDocument().addDocumentListener(new DocumentListener(){
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
		return champ.getText();
	}
	
	public void pop(String[] list){
		popup.setVisible(false);
		popup.removeAll();
		for(String s : list){
			menuItem = new JMenuItem(s);
			menuItem.setPreferredSize(new Dimension(champ.getWidth(),18));
			menuItem.setFont(champ.getFont());
			menuItem.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent ev) {
		        	champ.setText(s);
		        	popup.setVisible(false);
		        }
		    });
			menuItem.setBackground(Color.white);
			popup.add(menuItem);
		}
		popup.show(champ, 0, champ.getHeight());
		champ.requestFocus();
	}
	
	public String[] getEntry(String str){
		ArrayList<String> list = new ArrayList<String>();
		int i = 0;
		for(Ville v : Donnees.getInstance().getVilles()){
			if(i>=5) break;
			if(v.getNom().toLowerCase().contains(str.toLowerCase())){
				list.add(v.getNom());
				i++;
			}
		}
		for(Gare g : Donnees.getInstance().getGares()){
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
