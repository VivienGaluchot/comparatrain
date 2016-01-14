package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.NumberFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import comparaison.Comparateur;
import comparaison.Preference;

public class PanneauClient extends JPanel {
	

	private JButton connexion;
	
	private JLabel lblGareD;
	private JTextField texteD;
	private JLabel lblGareA;
	private JTextField texteA;
	
	private JComboBox<String> comboBox;
	private JTextField txtJjmmaaaa;
	private JTextField txtHeure;
	private JTextField txtMin;
	
	private JCheckBox cbDirect;
	private JLabel lblNbPlaces;
	private JComboBox<String> comboBoxNbPlaces;
	
	private JLabel lblClasse;
	private JRadioButton rbClasse1;
	private JRadioButton rbClasse2;
	
	
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
	
	
	public void connexionCompte(Comparateur comp,JTabbedPane onglets){
		PanneauAdmin PAdmin = new PanneauAdmin(comp,onglets);
		JTextField username = new JTextField();
    	JTextField password = new JPasswordField();
    	Object[] message = {
    	    "Username:", username,
    	    "Password:", password
    	};

    	int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
    	if (option == JOptionPane.OK_OPTION) {
    	    if (username.getText().equals("") && password.getText().equals("") && onglets.getTabCount()<2) {
    	    	setSize(550, 600);
    	    	onglets.addTab("Admin", null, PAdmin, null);
    	    	onglets.setSelectedIndex(1);
    	        System.out.println("Login successful");
    	    } else if(username.getText().equals("") && password.getText().equals("")){
    	    	onglets.setSelectedIndex(1);
    	    	setSize(550, 600);
    	        System.out.println("Already Loged In");
    	    } else {
    	    	onglets.setSelectedIndex(0);
    	        System.out.println("login failed");
    	    }
    	} else {
    		onglets.setSelectedIndex(0);
    	    System.out.println("Login canceled");
    	}
	}
	
	public PanneauClient(Comparateur comp,JTabbedPane onglets){
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel box5 = new JPanel();
		
		connexion = new JButton("Se connecter !");
		connexion.addActionListener(new ActionListener() {
			 
            public void actionPerformed(ActionEvent e)
            {
            	connexionCompte(comp,onglets);
            }
        });
		box5.add(connexion);
	this.add(box5);
		
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
		
		
		
		JPanel box6 = new JPanel();
		box6.setLayout(new BoxLayout(box6,BoxLayout.LINE_AXIS));
			
			
			JPanel box61 = new JPanel();
			
				box61.add(Box.createHorizontalStrut(7));
				cbDirect = new JCheckBox("Direct");
				box61.add(cbDirect);
				box61.add(Box.createHorizontalStrut(12));
				
				lblNbPlaces = new JLabel("Nb de places :");
				box61.add(lblNbPlaces);
			
				
				NumberFormat longFormat = NumberFormat.getIntegerInstance();
	
				NumberFormatter numberFormatter = new NumberFormatter(longFormat);
				numberFormatter.setValueClass(Long.class); //optional, ensures you will always get a long value
				numberFormatter.setAllowsInvalid(false); //this is the key!!
				numberFormatter.setMinimum(0l); //Optional
				//numberFormatter.setMaximum(100); 
	
				JFormattedTextField nbPlaces = new JFormattedTextField(numberFormatter);
				nbPlaces.setColumns(3);
				box61.add(nbPlaces);
				
				box61.add(Box.createHorizontalStrut(12));
				
				lblClasse = new JLabel("Classe : ");
				box61.add(lblClasse);
				rbClasse1 = new JRadioButton("1");
				rbClasse2 = new JRadioButton("2");
				rbClasse2.setSelected(true);
				ButtonGroup group = new ButtonGroup();
				group.add(rbClasse1);
				group.add(rbClasse2);
				box61.add(rbClasse1);
				box61.add(rbClasse2);
				box61.add(Box.createHorizontalStrut(7));
			box6.add(box61);
//			comboBoxNbPlaces = new JComboBox<String>();
//			comboBoxNbPlaces.setModel(new DefaultComboBoxModel<String>(new String[] {"1","2","3","4"}));
//			box6.add(comboBoxNbPlaces);
			
			box6.setBorder(BorderFactory.createTitledBorder("Option"));	
		this.add(box6);
		
		
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
	
	
	