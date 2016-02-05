package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import donnée.Donnees;
import elements.Gare;
import elements.Ville;
import utilisateur.Client;

@SuppressWarnings("serial")
public class PanneauAdmin extends JPanel{
	
	AdminFrame<Client> adminCli;
	AdminFrame<Ville> adminVille;
	AdminFrame<Gare> adminGare;
	AdminTrainFrame adminTrain;
	
	public PanneauAdmin(JTabbedPane onglets){
		
		adminTrain = new AdminTrainFrame();
		adminCli = new AdminFrame<Client>("Gestion des clients",Donnees.getInstance().getClients());
		adminVille = new AdminFrame<Ville>("Gestion des villes",Donnees.getInstance().getVilles());
		adminGare = new AdminFrame<Gare>("Gestion des gares",Donnees.getInstance().getGares());
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
	
		JPanel box2 = new JPanel();
			JButton adminVilleButton = new JButton("Ville");
			adminVilleButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminVille.afficher();
	        }});
			box2.add(adminVilleButton);
			
			JButton adminGaresButton = new JButton("Gares");
			adminGaresButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminGare.afficher();
	        }});
			box2.add(adminGaresButton);
		this.add(box2);
		
		JPanel box0 = new JPanel();
			JButton adminTrainButton = new JButton("Trains");
			adminTrainButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminTrain.afficher();
	        }});
			box0.add(adminTrainButton);
			
			JButton adminClientButton = new JButton("Clients");
			adminClientButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminCli.afficher();
	        }});
			box0.add(adminClientButton);
		this.add(box0);
		
		JPanel box4 = new JPanel();
			JButton deco = new JButton("Déconnexion");
			deco.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
	            	onglets.remove(1);
	        }});
			box4.add(deco);
			JButton save = new JButton("Sauvegarder");
			save.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
	            	// A FAIRE
	        }});
			box4.add(save);
		this.add(box4);
	}
}