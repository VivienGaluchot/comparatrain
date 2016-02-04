package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import comparaison.Comparateur;
import elements.Gare;
import elements.Ville;
import utilisateur.Client;

@SuppressWarnings("serial")
public class PanneauAdmin extends JPanel{
	
	AdminFrame<Client> adminCli;
	AdminFrame<Ville> adminVille;
	AdminFrame<Gare> adminGare;
	AdminTrainFrame adminTrain;
	
	public PanneauAdmin(Comparateur comp,JTabbedPane onglets){
		
		adminTrain = new AdminTrainFrame(comp.getData());
		adminCli = new AdminFrame<Client>("Clients",comp.getData().getClients(),comp.getData());
		adminVille = new AdminFrame<Ville>("Villes",comp.getData().getVilles(),comp.getData());
		adminGare = new AdminFrame<Gare>("Gares",comp.getData().getGares(),comp.getData());
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		
		JPanel box0 = new JPanel();
			JButton adminTrainButton = new JButton("Trains");
			adminTrainButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	            	adminTrain.setVisible(true);
	            }
	        });
			box0.add(adminTrainButton);
		this.add(box0);
		
		JPanel box1 = new JPanel();			
			JButton adminClientButton = new JButton("Client");
			adminClientButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	            	adminCli.setVisible(true);
	            }
	        });
			box1.add(adminClientButton);
		this.add(box1);
		
		JPanel box2 = new JPanel();			
			JButton adminGaresButton = new JButton("Gares");
			adminGaresButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	            	adminGare.setVisible(true);
	            }
	        });
			box2.add(adminGaresButton);
		this.add(box2);
		
		JPanel box3 = new JPanel();
			JButton adminVilleButton = new JButton("Ville");
			adminVilleButton.addActionListener(new ActionListener(){
	            public void actionPerformed(ActionEvent e)
	            {
	            	adminVille.setVisible(true);
	            }
	        });
			box3.add(adminVilleButton);
		this.add(box3);
		
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