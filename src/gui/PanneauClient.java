package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.NumberFormatter;

import comparaison.Comparateur;
import comparaison.Preference;
import elements.Horaire;
import gui.admin.PanneauAdmin;

@SuppressWarnings("serial")
public class PanneauClient extends JPanel {
	private JButton connexion;
	
	private VilleGareTextField texteD;
	private VilleGareTextField texteA;
	
	private ChampHoraire champHoraire;
	
	private JCheckBox cbDirect;
	private JLabel lblNbPlaces;
	
	private JLabel lblClasse;
	private JRadioButton rbClasse1;
	private JRadioButton rbClasse2;
	
	private JButton rechercher;
	private FenetreRes frameRes = null;	
	
	public PanneauClient(JTabbedPane onglets){
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel box5 = new JPanel();
		
		connexion = new JButton("Se connecter");
		connexion.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
        	connexionCompte(onglets);
        }});
		box5.add(connexion);
		this.add(box5);
	
		JPanel box0 = new JPanel();		
			box0.setLayout(new BoxLayout(box0,BoxLayout.PAGE_AXIS));
			texteD = new VilleGareTextField("Départ : ", "Nom de ville ou gare", 25);
			box0.add(texteD);
			texteA = new VilleGareTextField("Arrivée : ", "Nom de ville ou gare", 25);
			box0.add(texteA);
		box0.setBorder(BorderFactory.createTitledBorder("Trajet"));
		this.add(box0);
		
		JPanel box3 = new JPanel();
		
			champHoraire = new ChampHoraire("Départ","Arrivée");
			box3.add(champHoraire);
		
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
			
				SpinnerModel model = new SpinnerNumberModel(1,1,99,1);
				JSpinner nbPlaces = new JSpinner(model);
				JFormattedTextField txt = ((JSpinner.NumberEditor) nbPlaces.getEditor()).getTextField();
				((NumberFormatter) txt.getFormatter()).setAllowsInvalid(false);
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
			
			box6.setBorder(BorderFactory.createTitledBorder("Option"));	
		this.add(box6);
		
		JPanel box4 = new JPanel();
		
		rechercher = new JButton("Rechercher");
		rechercher.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        	Horaire h = champHoraire.getHoraire();
        	Preference pref = new Preference();
        	if(h.estInit()){
            	pref.setGares(texteD.getText(), texteA.getText());
				if(champHoraire.getComboBox().getSelectedItem().equals("Départ")){
					pref.setHDepart(h.toStringLong());	
				}else if(champHoraire.getComboBox().getSelectedItem().equals("Arrivée")){
					pref.setHArrivee(h.toStringLong());
				}
				pref.setDirect(cbDirect.isSelected());
                // Comparateur.comparer(pref).afficher();
                frameRes = new FenetreRes(Comparateur.comparer(pref));
                frameRes.setVisible(true);
        	}
        }});
		box4.add(rechercher);
	this.add(box4);
	}
	
	public void connexionCompte(JTabbedPane onglets){
		PanneauAdmin PAdmin = new PanneauAdmin(onglets);
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
}