package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends MyJFrame{
	private static final long serialVersionUID = 1L;
	
	public MainFrame() {
		setTitle("ComparaTrain");
		
		JTabbedPane onglets = new JTabbedPane();
		JPanel panneauClient = new PanneauClient(onglets,this);
        onglets.addTab("Accueil", null, panneauClient, null);
        
        add(onglets);
        
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		positionner();
        afficher();
	}
}