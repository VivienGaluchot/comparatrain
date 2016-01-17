package gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class MonTextField extends JPanel{
	JLabel label;
	JTextField textField;
	String initText;
	boolean init;
	JPopupMenu popup;
	JMenuItem menuItem;
	
	
	public MonTextField(String labelText, String initText, int lenght){
		label = new JLabel(labelText);
		this.add(label);
		this.initText = initText;
		init = true;
		textField = new JTextField(initText,lenght);
		textField.setForeground(Color.GRAY);
		ClearOnClick(textField);
		this.add(textField);
		
		popup = new JPopupMenu();
		String[] list = new String[2];
		list[0] = "Bonjour";
		list [1] = "Salut";
		
		textField.getDocument().addDocumentListener(new DocumentListener(){
			public void removeUpdate(DocumentEvent e){
				if(getText().length()>0 && !init)
					pop(list);
				}
			public void insertUpdate(DocumentEvent e){
				if(getText().length()>0 && !init)
					pop(list);
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
		popup.hide();
		popup.removeAll();
		for(String s : list)
			popup.add(new JMenuItem(s));
		//popup.show(this.getParent(), 0, 20);
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
				}
			}
	    });
	}
}
