package comparatrain;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import comparaison.Comparateur;
import comparaison.Preference;

public class PanneauClient extends JPanel {
	

	private JLabel lblGareD;
	private JTextField texteD;
	private JLabel lblGareA;
	private JTextField texteA;
	
	private JComboBox<String> comboBox;
	private JTextField txtJjmmaaaa;
	private JTextField txtHeure;
	private JTextField txtMin;
	
	private JButton rechercher;
	
	
	public void ClearOnClick(JTextField textField){
		String save = textField.getText();
		textField.addFocusListener(new FocusListener(){
	        public void focusGained(FocusEvent e){
	            textField.setText("");
	            textField.setForeground(Color.BLACK);
	        }

			@Override
			public void focusLost(FocusEvent e) {
				if (textField.getText().equals("")){
					textField.setForeground(new Color(100,100,100));
					textField.setText(save);
				}
				else textField.removeFocusListener(this);
			}
	    });
	}
	
	public PanneauClient(Comparateur comp){
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel box0 = new JPanel();
		
			box0.setLayout(new BoxLayout(box0,BoxLayout.PAGE_AXIS));
			JPanel box1 = new JPanel();
				lblGareD=new JLabel("Gare départ : ");
				box1.add(lblGareD);
				texteD = new JTextField("Gare de Lyon",14);
				Color color =new Color(100,100,100);
				texteD.setForeground(color);
				ClearOnClick(texteD);
				box1.add(texteD);
			box0.add(box1);
			JPanel box2 = new JPanel();
				lblGareA=new JLabel("Gare d'arrivé : ");
				box2.add(lblGareA);
				texteA = new JTextField("Marseille",14);
				texteA.setForeground(color);
				ClearOnClick(texteA);
				box2.add(texteA);
			box0.add(box2);
		box0.setBorder(BorderFactory.createTitledBorder("Trajet"));
		this.add(box0);
		
		JPanel box3 = new JPanel();
		
			comboBox = new JComboBox<String>();
			comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"départ", "arrivé"}));
			box3.add(comboBox);
			
			txtJjmmaaaa = new JTextField("jj/mm/aaaa",10);
			txtJjmmaaaa.setForeground(color);
			ClearOnClick(txtJjmmaaaa);
			box3.add(txtJjmmaaaa);
			
			txtHeure = new JTextField("heure",4);
			txtHeure.setForeground(color);
			ClearOnClick(txtHeure);
			box3.add(txtHeure);
			
			txtMin = new JTextField("min",4);
			txtMin.setForeground(color);
			ClearOnClick(txtMin);
			box3.add(txtMin);
		
		box3.setBorder(BorderFactory.createTitledBorder("Horaires"));
		this.add(box3);
		
		JPanel box4 = new JPanel();
			
			rechercher = new JButton("Rechercher!");
			rechercher.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	                Preference pref = new Preference();
	                pref.setGares(texteD.getText(), texteA.getText());
	                if (comboBox.getSelectedItem().equals("départ")){
	                	pref.setHDepart(txtJjmmaaaa.getText()+" "+ txtHeure.getText()+"h"+txtMin.getText());
	                }else{
	                	pref.setHArrive(txtJjmmaaaa.getText()+" "+ txtHeure.getText()+"h"+txtMin.getText());
	                }
	                comp.comparer(pref).afficher();
	            }
	        });
			box4.add(rechercher);
		this.add(box4);
	}
}
	
	
	