package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import comparaison.Comparateur;
import comparaison.Preference;
import donnee.Donnees;
import elements.Horaire;
import gui.admin.PanneauAdmin;
import utilisateur.Client;

@SuppressWarnings("serial")
public class PanneauClient extends JPanel {
	private JButton connexion;
	private JButton deconnexion;
	private JLabel infoClient;
	
	private VilleGareTextField texteD;
	private VilleGareTextField texteA;
	
	private ChampHoraire champHoraire;
	
	private JCheckBox cbDirect;
	private SpinnerChamp nbPlaces;
	
	private JRadioButton rbClasse1;
	private JRadioButton rbClasse2;
	
	private JButton rechercher;
	private FenetreRes frameRes = null;	
	private MyJFrame parent;
	
	
	public PanneauClient(JTabbedPane onglets,MyJFrame p){
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		parent = p;
		JPanel box5 = new JPanel();
		
		infoClient = new JLabel();
		infoClient.setVisible(true);
		box5.add(infoClient);
		
		connexion = new JButton("Se connecter");
		connexion.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
        	connexionCompte(onglets);
        }});
		box5.add(connexion);
		this.add(box5);
		deconnexion = new JButton("Se deconnecter");
		deconnexion.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	    	Client.disconnect();
        	infoClient.setText(null);
        	infoClient.setVisible(false);
        	connexion.setVisible(true);
        	deconnexion.setVisible(false);
        	if(onglets.getTabCount()==2)onglets.remove(1);
			parent.positionner();
        }});
		deconnexion.setVisible(false);
		box5.add(deconnexion);
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
				
				nbPlaces = new SpinnerChamp("Nb de places :",99);
				box61.add(nbPlaces);
				
				box61.add(Box.createHorizontalStrut(12));
				
				JLabel lblClasse = new JLabel("Classe : ");
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
        	if(h.estInit()){
        		int classe;
				if(rbClasse1.isSelected())
					classe = 1;
				else 
					classe = 2;
        		Preference pref;
				if(champHoraire.getComboBox().getSelectedItem().equals("Départ")){
					pref = new Preference(texteD.getText(),h,texteA.getText(),null,cbDirect.isSelected(),nbPlaces.getValue(),classe);
				}else{
					pref = new Preference(texteD.getText(),null,texteA.getText(),h,cbDirect.isSelected(),nbPlaces.getValue(),classe);
				}
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
    			connexion.setVisible(false);
    			deconnexion.setVisible(true);
    			parent.positionner();
    	        System.out.println("Login admin successful");
    	    } else if(username.getText().equals("") && password.getText().equals("")){
    	    	onglets.setSelectedIndex(1);
    			connexion.setVisible(false);
    			deconnexion.setVisible(true);
    			parent.positionner();
    	    	setSize(550, 600);
    	        System.out.println("Already Loged In as admin");
    	    } else if(Client.connect(username.getText(),password.getText()) != null){
    	    	onglets.setSelectedIndex(0);
    	    	
    	    	infoClient.setText("Bienvenue "+username.getText());
    	    	infoClient.setVisible(true);
    			connexion.setVisible(false);
    			deconnexion.setVisible(true);
    			parent.positionner();
    	        System.out.println("login client succes");
    	    }else{
    	    	onglets.setSelectedIndex(0);
    	        System.out.println("login failed");
    	    }
    	} else {
    		onglets.setSelectedIndex(0);
    	    System.out.println("Login canceled");
    	}
	}
}