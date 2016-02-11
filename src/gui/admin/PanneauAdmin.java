package gui.admin;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import donnee.Donnees;
import elements.Billet;
import elements.Gare;
import elements.Ville;
import train.Rame;
import train.Train;
import utilisateur.Client;

@SuppressWarnings("serial")
public class PanneauAdmin extends JPanel{
	
	AdminFrame<Client> adminCli;
	AdminFrame<Ville> adminVille;
	AdminFrame<Gare> adminGare;
	AdminFrame<Train> adminTrain;
	AdminFrame<Billet> adminBillet;
	AdminFrame<Rame> adminRame;
	
	
	public PanneauAdmin(JTabbedPane onglets){
		
		adminTrain = new AdminFrame<Train>("Gestion des trains",Train.class,Donnees.getTrains());
		adminCli = new AdminFrame<Client>("Gestion des clients",Client.class,Donnees.getClients());
		adminVille = new AdminFrame<Ville>("Gestion des villes",Ville.class,Donnees.getVilles());
		adminGare = new AdminFrame<Gare>("Gestion des gares",Gare.class,Donnees.getGares());
		adminBillet = new AdminFrame<Billet>("Gestion des billets",Billet.class,Donnees.getBillets());
		adminRame = new AdminFrame<Rame>("Gestion des rames",Rame.class,Donnees.getRames());
		
		this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
		Dimension d = new Dimension(80,20);
		JPanel box1 = new JPanel();
			JLabel adminVilleLbl = new JLabel("Villes : " );
			adminVilleLbl.setPreferredSize(d);
			JButton adminVilleButton = new JButton("Editer");
			adminVilleButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminVille.afficher();
	        }});
			box1.add(Box.createHorizontalGlue());
			box1.add(adminVilleLbl);
			box1.add(adminVilleButton);
			box1.add(Box.createRigidArea(new Dimension(20,0)));
		this.add(box1);
			
		JPanel box2 = new JPanel();
			JLabel adminGaresLbl = new JLabel("Gares : ");
			adminGaresLbl.setPreferredSize(d);
			JButton adminGaresButton = new JButton("Editer");
			adminGaresButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminGare.afficher();
	        }});
			box2.add(Box.createHorizontalGlue());
			box2.add(adminGaresLbl);
			box2.add(adminGaresButton);
			box2.add(Box.createRigidArea(new Dimension(20,0)));
		this.add(box2);
		
		JPanel box3 = new JPanel();
			JLabel adminTrainLbl = new JLabel("Trains : ");
			adminTrainLbl.setPreferredSize(d);
			JButton adminTrainButton = new JButton("Editer");
			adminTrainButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminTrain.afficher();
	        }});
			box3.add(Box.createHorizontalGlue());
			box3.add(adminTrainLbl);
			box3.add(adminTrainButton);
			box3.add(Box.createRigidArea(new Dimension(20,0)));
		this.add(box3);	
		
		JPanel box7 = new JPanel();
			JLabel adminRameLbl = new JLabel("Rames : ");
			adminRameLbl.setPreferredSize(d);
			JButton adminRameButton = new JButton("Editer");
			adminRameButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminRame.afficher();
	        }});
			box7.add(Box.createHorizontalGlue());
			box7.add(adminRameLbl);
			box7.add(adminRameButton);
			box7.add(Box.createRigidArea(new Dimension(20,0)));
		this.add(box7);	
			
		JPanel box4 = new JPanel();	
			JLabel adminClientLbl = new JLabel("Clients : ");
			adminClientLbl.setPreferredSize(d);
			JButton adminClientButton = new JButton("Editer");
			adminClientButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminCli.afficher();
	        }});
			box4.add(Box.createHorizontalGlue());
			box4.add(adminClientLbl);
			box4.add(adminClientButton);
			box4.add(Box.createRigidArea(new Dimension(20,0)));
		this.add(box4);
		
		JPanel box6 = new JPanel();	
			JLabel adminBilletLbl = new JLabel("Billet : ");
			adminBilletLbl.setPreferredSize(d);
			JButton adminBilletButton = new JButton("Editer");
			adminBilletButton.addActionListener(new ActionListener(){ public void actionPerformed(ActionEvent e){
	            	adminBillet.afficher();
	        }});
			box6.add(Box.createHorizontalGlue());
			box6.add(adminBilletLbl);
			box6.add(adminBilletButton);
			box6.add(Box.createRigidArea(new Dimension(20,0)));
		this.add(box6);
		
		JPanel box5 = new JPanel();
		
			JButton save = new JButton("Sauvegarder");
			save.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e){
	            	Donnees.sauvegarder();
	        }});
			box5.add(save);
		this.add(box5);
		

	}
}