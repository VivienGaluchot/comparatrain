package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import defaut.MainActivity;
import donnee.Donnees;
import elements.Horaire;
import elements.Preference;
import gui.elements.ChampHoraire;
import gui.elements.PanneauGroupe;
import gui.elements.MyJFrame;
import gui.elements.SpinnerChamp;
import offre.Comparateur;
import offre.Resultat;
import gui.elements.PropTextField;
import train.Siege;
import train.Wagon;

@SuppressWarnings("serial")
public class PanneauClient extends JPanel {
	private JButton connexion;
	private JButton deconnexion;
	private JLabel infoClient;
	
	private PropTextField texteD;
	private PropTextField texteA;	
	private ChampHoraire champHoraire;	
	private JCheckBox cbDirect;
	private SpinnerChamp nbPlaces;	
	private JCheckBox cbWBar;
	private JRadioButton rbClasse1;
	private JRadioButton rbClasse2;	
	private JRadioButton rbSensAvant;
	private JRadioButton rbSensArriere;
	private JRadioButton rbCouloir;
	private JRadioButton rbFenetre;
	
	private JButton rechercher;
	private FenetreRes frameRes = null;	
	private MyJFrame parent;
	

	
	public PanneauClient(final JTabbedPane onglets,MyJFrame p){

		
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
	    	MainActivity.disconnect();
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
			texteD = new PropTextField("Départ : ", "Nom de ville ou gare", 25, Donnees.getStrLieux());
			box0.add(texteD);
			texteA = new PropTextField("Arrivée : ", "Nom de ville ou gare", 25, Donnees.getStrLieux());
			box0.add(texteA);
		box0.setBorder(BorderFactory.createTitledBorder("Trajet"));
		this.add(box0);
		
		JPanel box3 = new JPanel();
			champHoraire = new ChampHoraire("Départ","Arrivée");
			box3.add(champHoraire);
		
		box3.setBorder(BorderFactory.createTitledBorder("Horaires"));
		this.add(box3);
		
		JPanel box6 = new PanneauGroupe("Options");			
			JPanel box61 = new JPanel();
				cbDirect = new JCheckBox("Train direct");
				box61.add(cbDirect);
				box61.add(Box.createHorizontalStrut(12));
				
				nbPlaces = new SpinnerChamp("Nombre de billets : ",99);
				box61.add(nbPlaces);
			box6.add(box61);
			
			JPanel box62 = new JPanel();
			box62.setLayout(new BoxLayout(box62,BoxLayout.LINE_AXIS));
				PanneauGroupe gp = new PanneauGroupe("Wagon");
					rbClasse1 = new JRadioButton("Première classe");
					rbClasse2 = new JRadioButton("Seconde classe");
					JRadioButton indif = new JRadioButton("Indifférent");
					indif.setSelected(true);
					cbWBar = new JCheckBox("Voiture bar recquise");
					ButtonGroup group = new ButtonGroup();
					group.add(indif);
					group.add(rbClasse1);
					group.add(rbClasse2);
					gp.add(indif);
					gp.add(rbClasse1);
					gp.add(rbClasse2);
					gp.add(new JSeparator(SwingConstants.HORIZONTAL));
					gp.add(cbWBar);
				box62.add(gp);
				
				gp = new PanneauGroupe("Place");
					group = new ButtonGroup();
					rbSensAvant = new JRadioButton("Sens de la marche");
					rbSensArriere = new JRadioButton("Marche arrière");
					indif = new JRadioButton("Indifférent");
					indif.setSelected(true);
					group.add(indif);
					group.add(rbSensAvant);
					group.add(rbSensArriere);
					gp.add(indif);
					gp.add(rbSensAvant);
					gp.add(rbSensArriere);
					gp.add(new JSeparator(SwingConstants.HORIZONTAL));
					group = new ButtonGroup();
					rbCouloir = new JRadioButton("Côté couloir");
					rbFenetre = new JRadioButton("Côté fenêtre");
					indif = new JRadioButton("Indifférent");
					indif.setSelected(true);
					group.add(indif);
					group.add(rbCouloir);
					group.add(rbFenetre);
					gp.add(indif);
					gp.add(rbCouloir);
					gp.add(rbFenetre);
				box62.add(gp);
			box6.add(box62);
		this.add(box6);
		
		JPanel box4 = new JPanel();
		
		rechercher = new JButton("Rechercher");
		rechercher.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){
        	Horaire h = champHoraire.getHoraire();
        	if(h.estInit()){       		
        		Horaire hDep = null;
        		Horaire hArr = null;
				if(champHoraire.getComboBox().getSelectedItem().equals("Départ")){
					hDep = h;
				}else{
					hArr = h;
				}
				
				Preference pref = new Preference(texteD.getText(),hDep,texteA.getText(),hArr);
				
				// Options
				// Direct
				pref.setDirect(cbDirect.isSelected());
				// NbPlaces
				pref.setNbPlace(nbPlaces.getValue());
				// Classe
				if(rbClasse1.isSelected())
					pref.setClasse(Wagon.PREMIERE);
				else if(rbClasse2.isSelected())
					pref.setClasse(Wagon.SECONDE);
				// Cote
				if(rbCouloir.isSelected())
					pref.setCote(Siege.COULOIR);
				else if(rbFenetre.isSelected())
					pref.setCote(Siege.FENETRE);
				// Sens
        		if(rbSensAvant.isSelected())
        			pref.setSens(Siege.AVANT);
				else if(rbSensArriere.isSelected())
        			pref.setSens(Siege.ARRIERE);
        		// Bar
				pref.setBar(cbWBar.isSelected());
				
				Resultat resultats = Comparateur.comparer(pref,Donnees.getBillets());

                frameRes = new FenetreRes(resultats);
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
    	    } else if(MainActivity.connect(username.getText(),password.getText()) != null){
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