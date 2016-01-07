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
import modele.Gare;

public class PanneauAdmin extends JPanel{
	
	private JLabel lblGareD;
	private JComboBox<Gare> comboBoxD;
	private JLabel lblGareA;
	private JComboBox<Gare> comboBoxA;
	private JLabel lblGareE;
	private JComboBox<Gare> comboBoxE;
	private JLabel lblHoraires;
	
	private JTextField txtJjmmaaaa1;
	private JTextField txtHeure1;
	private JTextField txtMin1;
	
	private JTextField txtJjmmaaaa2;
	private JTextField txtHeure2;
	private JTextField txtMin2;
	
	private JTextField txtJjmmaaaa3;
	private JTextField txtHeure3;
	private JTextField txtMin3;
	
	private JTextField txtJjmmaaaa4;
	private JTextField txtHeure4;
	private JTextField txtMin4;
	
	private JButton ajouter;
	
	
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
	
	public PanneauAdmin(Comparateur comp){
		
		Color color =new Color(100,100,100);
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel box1 = new JPanel();
		
			box1.setLayout(new BoxLayout(box1,BoxLayout.PAGE_AXIS));
			JPanel box11 = new JPanel();
				lblGareD = new JLabel("Gare départ : ");
			box11.add(lblGareD);
				comboBoxD = new JComboBox<Gare>();
				//comboBoxD.setModel(new DefaultComboBoxModel<Gare>(comp));
			box11.add(comboBoxD);
		box1.add(box11);
			JPanel box12 = new JPanel();
				lblHoraires = new JLabel("Horaires : ");
				box12.add(lblHoraires);
				txtJjmmaaaa1 = new JTextField("jj/mm/aaaa",10);
				txtJjmmaaaa1.setForeground(color);
				ClearOnClick(txtJjmmaaaa1);
				box12.add(txtJjmmaaaa1);
				
				txtHeure1 = new JTextField("heure",4);
				txtHeure1.setForeground(color);
				ClearOnClick(txtHeure1);
				box12.add(txtHeure1);
				
				txtMin1 = new JTextField("min",4);
				txtMin1.setForeground(color);
				ClearOnClick(txtMin1);
			box12.add(txtMin1);
		box1.add(box12);
		box1.setBorder(BorderFactory.createTitledBorder("Départ"));
		this.add(box1);
		
		
			
		
		JPanel box2 = new JPanel();
			box2.setLayout(new BoxLayout(box2,BoxLayout.PAGE_AXIS));
			JPanel box21 = new JPanel();
			lblHoraires = new JLabel("Horaires d'arrivée : ");
			box21.add(lblHoraires);
			txtJjmmaaaa2 = new JTextField("jj/mm/aaaa",10);
			txtJjmmaaaa2.setForeground(color);
			ClearOnClick(txtJjmmaaaa2);
			box21.add(txtJjmmaaaa2);
			
			txtHeure2 = new JTextField("heure",4);
			txtHeure2.setForeground(color);
			ClearOnClick(txtHeure2);
			box21.add(txtHeure2);
			
			txtMin2 = new JTextField("min",4);
			txtMin2.setForeground(color);
			ClearOnClick(txtMin2);
			box21.add(txtMin2);
		box2.add(box21);
		
		JPanel box22 = new JPanel();
			lblGareE = new JLabel("Escale : ");
			box22.add(lblGareE);
			comboBoxE = new JComboBox<Gare>();
			//comboBoxE.setModel(new DefaultComboBoxModel<Gare>(comp));
			box22.add(comboBoxE);
		box2.add(box22);
		
		JPanel box23 = new JPanel();
			lblHoraires = new JLabel("Horaires de départ : ");
			box23.add(lblHoraires);
			txtJjmmaaaa3 = new JTextField("jj/mm/aaaa",10);
			txtJjmmaaaa3.setForeground(color);
			ClearOnClick(txtJjmmaaaa3);
			box23.add(txtJjmmaaaa3);
			
			txtHeure3 = new JTextField("heure",4);
			txtHeure3.setForeground(color);
			ClearOnClick(txtHeure3);
			box23.add(txtHeure3);
			
			txtMin3 = new JTextField("min",4);
			txtMin3.setForeground(color);
			ClearOnClick(txtMin3);
			box23.add(txtMin3);
		box2.add(box23);
			
		
		box2.setBorder(BorderFactory.createTitledBorder("Escales"));
		this.add(box2);
		
		
		JPanel box3 = new JPanel();
		
		box3.setLayout(new BoxLayout(box3,BoxLayout.PAGE_AXIS));
		JPanel box31 = new JPanel();
			lblGareA = new JLabel("Gare d'arrivée : ");
			box31.add(lblGareA);
			comboBoxA = new JComboBox<Gare>();
			//comboBoxA.setModel(new DefaultComboBoxModel<Gare>(comp));
			box31.add(comboBoxA);
		box3.add(box31);
		JPanel box32 = new JPanel();
			lblHoraires = new JLabel("Horaires : ");
			box32.add(lblHoraires);
			txtJjmmaaaa4 = new JTextField("jj/mm/aaaa",10);
			txtJjmmaaaa4.setForeground(color);
			ClearOnClick(txtJjmmaaaa4);
			box32.add(txtJjmmaaaa4);
			
			txtHeure4 = new JTextField("heure",4);
			txtHeure4.setForeground(color);
			ClearOnClick(txtHeure4);
			box32.add(txtHeure4);
			
			txtMin4 = new JTextField("min",4);
			txtMin4.setForeground(color);
			ClearOnClick(txtMin4);
			box32.add(txtMin4);
		box3.add(box32);
	box3.setBorder(BorderFactory.createTitledBorder("Arrivée"));
	this.add(box3);
		
		
		JPanel box5 = new JPanel();
			
			ajouter = new JButton("Ajouter!");
			ajouter.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	                
	            }
	        });
			box5.add(ajouter);
		this.add(box5);
	}
}
	
	
	
