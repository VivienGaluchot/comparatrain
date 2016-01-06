package comparatrain;


import java.awt.Color;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MaFenetre extends JFrame{
	private JLabel lblGareD;
	private JTextField texteD;
	private JLabel lblGareA;
	private JTextField texteA;
	
	private JComboBox<String> comboBox;
	private JTextField txtJjmmaaaa;
	private JTextField txtHeure;
	private JTextField txtMin;
	
	private JButton rechercher;
	
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

	
	public MaFenetre(String titre, int x, int y, int l, int h) {
		
		Comparateur Comp = new Comparateur();
		Comp.ajouterOffresTest();
		
		setTitle(titre);
		//setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
		setBounds(x,y,l,h);
		
		JPanel box = new JPanel();
		box.setLayout(new BoxLayout(box,BoxLayout.PAGE_AXIS));
		
		JPanel box0 = new JPanel();
	
		
			box0.setLayout(new BoxLayout(box0,BoxLayout.PAGE_AXIS));
			JPanel box1 = new JPanel();
				lblGareD=new JLabel("Gare départ : ");
				box1.add(lblGareD);
				texteD = new JTextField("ex:Gare de Lyon",14);
				Color color =new Color(100,100,100);
				texteD.setForeground(color);
				ClearOnClick(texteD);
				box1.add(texteD);
			box0.add(box1);
			JPanel box2 = new JPanel();
				lblGareA=new JLabel("Gare d'arrivé : ");
				box2.add(lblGareA);
				texteA = new JTextField("ex:Gare de Marseille",14);
				texteA.setForeground(color);
				ClearOnClick(texteA);
				box2.add(texteA);
			box0.add(box2);
		box0.setBorder(BorderFactory.createTitledBorder("Trajet"));
		box.add(box0);
		
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
		box.add(box3);
		
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
	                Comp.comparer(pref).afficher();
	            }
	        });
			box4.add(rechercher);
		box.add(box4);
		add(box);
		
		WindowListener exitListener = new MaWindow();
		addWindowListener(exitListener);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
