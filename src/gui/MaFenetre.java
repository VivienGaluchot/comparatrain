package gui;

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
		setBounds(largeur/2-215,hauteur/2-170,430,340);
		//setResizable(false);
		
		JTabbedPane onglets = new JTabbedPane();
		JPanel PClient = new PanneauClient(c,onglets);
	  
        onglets.addTab("Acceuil", null, PClient, null); //
        
        onglets.addChangeListener(new ChangeListener() { //add the Listener
        	
        	@Override
            public void stateChanged(ChangeEvent e) {
                if(onglets.getSelectedIndex()==1 ) {//Index starts at 0, so Index 1 = onglet2
                	pack();
                }
                else if(onglets.getSelectedIndex()==0){
                	pack();
                }
            }
        });
        
        add(onglets);
        
        pack();
	  
		WindowListener exitListener = new MaWindow();
		//addWindowListener(exitListener);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}