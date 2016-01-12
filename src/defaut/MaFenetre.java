package defaut;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import comparaison.Comparateur;

public class MaFenetre extends JFrame{
	private static final long serialVersionUID = 1L;
	
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
	
	public MaFenetre(String titre, Comparateur c) {
		setTitle(titre);
		comp = c;
			
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int largeur = dim.width;
		int hauteur = dim.height;
		//setLayout(new FlowLayout(FlowLayout.CENTER,10,15));
		setBounds(largeur/2-175,hauteur/2-140,350,280);
		setResizable(false);
		
		JTabbedPane onglets = new JTabbedPane();
		JPanel PClient = new PanneauClient(c);
		PanneauAdmin PAdmin = new PanneauAdmin();
	  
        onglets.addTab("Client", null, PClient, null); //
        onglets.addTab("Admin", null, PAdmin, null); //
        
        onglets.addChangeListener(new ChangeListener() { //add the Listener
        	
        	@Override
            public void stateChanged(ChangeEvent e) {
                if(onglets.getSelectedIndex()==1 && PAdmin.getComponentCount()==0) {//Index starts at 0, so Index 1 = onglet2
                	JTextField username = new JTextField();
                	JTextField password = new JPasswordField();
                	Object[] message = {
                	    "Username:", username,
                	    "Password:", password
                	};

                	int option = JOptionPane.showConfirmDialog(null, message, "Login", JOptionPane.OK_CANCEL_OPTION);
                	if (option == JOptionPane.OK_OPTION) {
                	    if (username.getText().equals("") && password.getText().equals("")) {
                	    	setSize(550, 600);
                	    	PAdmin.remplirPanneauAdmin(c);
                	        System.out.println("Login successful");
                	    } else {
                	    	onglets.setSelectedIndex(0);
                	        System.out.println("login failed");
                	    }
                	} else {
                		onglets.setSelectedIndex(0);
                	    System.out.println("Login canceled");
                	}      
                }
                else if(onglets.getSelectedIndex()==1){
                	setSize(550, 600);
                }
                else if(onglets.getSelectedIndex()==0){
                	setSize(350, 280);
                }
            }
        });
        
        add(onglets);
	  
		WindowListener exitListener = new MaWindow();
		//addWindowListener(exitListener);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}