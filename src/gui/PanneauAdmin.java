package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import comparaison.Comparateur;

@SuppressWarnings("serial")
public class PanneauAdmin extends JPanel{
	
	AdminTrainFrame adminTrain;
	
	public PanneauAdmin(Comparateur comp,JTabbedPane onglets){
		
		adminTrain = new AdminTrainFrame(comp);
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel box1 = new JPanel();
			JButton adminTrainButton = new JButton("Trains");
			adminTrainButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	            	adminTrain.setVisible(true);
	            }
	        });
			box1.add(adminTrainButton);
		this.add(box1);
		
		JPanel box4 = new JPanel();
			JButton deco = new JButton("Déconnexion");
			deco.addActionListener(new ActionListener() {
				 
	            public void actionPerformed(ActionEvent e)
	            {
	            	onglets.remove(1);
	                
	            }
	        });
			box4.add(deco);
		this.add(box4);
	}
}