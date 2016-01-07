package comparatrain;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MaFenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	
	// Interface
	private JLabel lblGareD;
	private JTextField texteD;
	private JLabel lblGareA;
	private JTextField texteA;
	
	private JComboBox<String> comboBox;
	private JTextField txtJjmmaaaa;
	private JTextField txtHeure;
	private JTextField txtMin;
	
	private JButton rechercher;
	
	// Systeme
	Comparateur comp;
	
	public class MaWindow extends WindowAdapter{
		public void windowClosing(WindowEvent e){
			//A changer pour mettre dans la console
			int confirm = JOptionPane.showOptionDialog(
				null, "Are You Sure to Close Application?", 
				"Exit Confirmation", JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, null, null);
	        if (confirm == 0) {
	           System.exit(0);
	        }
		}
	}
	
	public JPanel PanneauClient(){
		
		JPanel panneau = new JPanel();
		panneau.setLayout(new BoxLayout(panneau,BoxLayout.PAGE_AXIS));
		
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
		panneau.add(box0);
		
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
		panneau.add(box3);
		
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
		panneau.add(box4);
		return panneau;
	}
	
	public JPanel PanneauAdmin(){
		
		JPanel panneau = new JPanel();
		panneau.setLayout(new BoxLayout(panneau,BoxLayout.PAGE_AXIS));
		
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
		panneau.add(box0);
		
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
		panneau.add(box3);
		
		JPanel box4 = new JPanel();
			
			rechercher = new JButton("Ajouter!");
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
		panneau.add(box4);
		return panneau;
	}
	
	public MaFenetre(String titre, Comparateur c) {
		setTitle(titre);
		comp = c;
			
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width;
		
		int hauteur = dim.height;
		//setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
		setBounds(3*largeur/8,3*hauteur/8,largeur/4,3*hauteur/8);
		
		
		
		JTabbedPane onglets = new JTabbedPane();
		JPanel vide = new JPanel();
	  
        onglets.addTab("Client", null, PanneauClient(), null); //
        onglets.addTab("Admin", null, vide, null); //
        

        onglets.addChangeListener(new ChangeListener() { //add the Listener
        	
        JLabel badLog = new JLabel("Mauvais login");

        	@Override
            public void stateChanged(ChangeEvent e) {
                if(onglets.getSelectedIndex()==1 && vide.getComponentCount()==0) //Index starts at 0, so Index 1 = onglet2
                {
                	
                	
                	JTextField username = new JTextField();
                	JTextField password = new JPasswordField();
                	Object[] message = {
                	    "Username:", username,
                	    "Password:", password
                	};

                	int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
                	if (option == JOptionPane.OK_OPTION) {
                	    if (username.getText().equals("admin") && password.getText().equals("admin")) {
                	    	vide.setLayout(new BoxLayout(vide,BoxLayout.PAGE_AXIS));
                	    	vide.add(PanneauAdmin());
                	        System.out.println("Login successful");
                	    } else {
                	    	vide.add(badLog);
                	        System.out.println("login failed");
                	    }
                	} else {
                	    System.out.println("Login canceled");
                	}      

                }if(onglets.getSelectedIndex()==0 && vide.getComponent(0).equals(badLog)) vide.removeAll();
            }
        });
        
        add(onglets);
	  
		
		WindowListener exitListener = new MaWindow();
		addWindowListener(exitListener);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
	}
	
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
    
}


