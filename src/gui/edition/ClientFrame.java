package gui.edition;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import gui.elements.ChampTextField;
import gui.elements.PanneauGroupe;
import gui.elements.MyJFrame;
import gui.elements.SpinnerChamp;

import donnee.Donnees;
import elements.Erreur;
import utilisateur.Client;

@SuppressWarnings("serial")
public class ClientFrame extends MyJFrame{
	Client client;
	PanneauListe<Client> father;
	boolean nouveau;
	
	private SpinnerChamp id;
	private ChampTextField prenom;
	private ChampTextField nom;
	private ChampTextField login;
	private ChampTextField motdepasse;
	
	private JButton valider;
	
	public ClientFrame(Client c, PanneauListe<Client> f){
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
			id = new SpinnerChamp("Id : ",9999);
			if(client.getId() != null)
				id.setValue(client.getId());
			else
				id.setValue(Donnees.clients.getFreeId());
			box.add(id);
		main.add(box);
		
		box = new PanneauGroupe("Personne");
		prenom = new ChampTextField("Prenom : ");
		prenom.setText(client.getPrenom());
		nom = new ChampTextField("Nom : ");
		nom.setText(client.getNom());
		box.add(prenom);
		box.add(nom);
		main.add(box);
		
		box = new PanneauGroupe("Client");
		login = new ChampTextField("Login : ");
		login.setText(client.getLogin());
		motdepasse = new ChampTextField("Mot de passe : ");
		motdepasse.setText(client.getMotDePasse());
		box.add(login);
		box.add(motdepasse);
		main.add(box);
		
		box = new JPanel();
		JButton annuler = new JButton("Annuler");
		annuler.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
        	setVisible(false);
        }});
		box.add(annuler);
		valider = new JButton("Valider");
		valider.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) {
        	try {
        		Donnees.clients.changeId(client, id.getValue());
        		client.setPrenom(prenom.getText());
        		client.setNom(nom.getText());
        		client.setLogin(login.getText());
        		client.setMotDePasse(motdepasse.getText());
        		if(nouveau)
        			Donnees.clients.add(client);
            	father.majList();
            	setVisible(false);
			} catch (Erreur e1) {
				if(e1.getType() == Erreur.EXISTE)
					id.setWrong(true);
				else{
					prenom.setWrong(true);
					nom.setWrong(true);
					login.setWrong(true);
					motdepasse.setWrong(true);
				}
				System.out.println(e1);
			}
		}});
		box.add(valider);
	main.add(box);
	
	add(main);
	
	positionner();
	}
}
