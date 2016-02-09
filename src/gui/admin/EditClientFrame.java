package gui.admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import defaut.Erreur;
import donnee.Donnees;
import elements.Escale;
import elements.GareHoraire;
import gui.ChampHoraire;
import gui.ChampTextField;
import gui.GareComboBox;
import gui.GroupPanel;
import gui.ListPanel;
import gui.MyJFrame;
import gui.SpinnerChamp;
import train.Train;
import utilisateur.Client;

public class EditClientFrame extends MyJFrame{
	
	Client client;
	ListPanel<Client> father;
	boolean nouveau;
	
	private ChampTextField prenom;
	private ChampTextField nom;
	private ChampTextField login;
	private ChampTextField motdepasse;
	
	private JButton valider;
	
	public EditClientFrame(Client c, ListPanel<Client> f){
		if(c == null){
			client = new Client();
			nouveau = true;
		}
		else{
			client = c;
			nouveau = false;
		}
		
		father = f;
		
		setTitle("Edition");
		
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main,BoxLayout.PAGE_AXIS));
		
		
		JPanel box = new JPanel();
			SpinnerChamp id = new SpinnerChamp("Id : ",9999);
			box.add(id);
		main.add(box);
		
		box = new GroupPanel("Personne");
		prenom = new ChampTextField("Prenom");
		prenom.setText(client.getPrenom());
		nom = new ChampTextField("Nom");
		nom.setText(client.getNom());
		box.add(prenom);
		box.add(nom);
		main.add(box);
		
		box = new GroupPanel("Client");
		login = new ChampTextField("Login");
		login.setText(client.getLogin());
		motdepasse = new ChampTextField("MotDePasse");
		motdepasse.setText(client.getMotDePasse());
		box.add(login);
		box.add(motdepasse);
		main.add(box);

		
		box = new JPanel();
		valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
            	try {
            		client.setId((Integer) id.getValue());
            		client.setPrenom(prenom.getText());
            		client.setNom(nom.getText());
            		client.setLogin(login.getText());
            		client.setMotDePasse(motdepasse.getText());
            		if(nouveau)
            			Donnees.getInstance().addClient(client);;
	            	father.majList();
	            	setVisible(false);
				} catch (Erreur e1) {
					prenom.setWrong(true);
					nom.setWrong(true);
					login.setWrong(true);
					motdepasse.setWrong(true);

					System.out.println(e1);
				}
            }
        });
		box.add(valider);
	main.add(box);
	}
}
