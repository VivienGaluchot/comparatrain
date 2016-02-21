package gui.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Champ text à proposition
 */
@SuppressWarnings("serial")
public class PropTextField extends Champ<JTextField>{
	String initText;
	boolean init;
	JPopupMenu popup;
	JMenuItem menuItem;	
	List<String> prop;
	
	public PropTextField(String labelText, String initText, int lenght, List<String> prop){
		super(labelText, new JTextField(initText,lenght));
		
		this.initText = initText;
		init = true;
		
		this.prop = prop;

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
		for(final String s : list){
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
		List<String> list = new ArrayList<String>();
		int i = 0;
		for(String s : prop){
			if(i>=10) break;
			if(s.toLowerCase().contains(str.toLowerCase())){
				list.add(s);
				i++;
			}
		}
		list.sort(null);
		String [] res = new String[list.size()];
		list.toArray(res);
		return res;
	}
	
	public void ClearOnClick(final JTextField textField){
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
