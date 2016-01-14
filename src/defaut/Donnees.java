package defaut;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modele.Gare;
import modele.Train;
import modele.Ville;

import utilisateur.Client;

import yamlbeans.YamlException;
import yamlbeans.YamlReader;
import yamlbeans.YamlWriter;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Gestion des données, enregistrements
 */
public class Donnees {
	private ArrayList<Client> clients;
	private ArrayList<Ville> villes;
	private ArrayList<Gare> gares;
	private ArrayList<Train> trains;
	
	public Donnees(){
		villes = new ArrayList<Ville>();
		gares = new ArrayList<Gare>();
		trains = new ArrayList<Train>();
	}
	
	public void afficher(){
		for(Ville v : villes){
			System.out.println(v);
		}
		System.out.println("\n\n");
		for(Gare g : gares){
			System.out.println(g);
		}
		System.out.println("\n\n");
		for(Train t : getTrains()){
			System.out.println(t);
		}
	}
	
	// Données
	public void addClient(Client client) throws Erreur{
		for(Client c : clients)
			if(client.getId() == c.getId()) throw new Erreur(Erreur.EXISTE);
		clients.add(client);
	}
	
	public void addVille(Ville ville) throws Erreur{
		for(Ville v : villes)
			if(ville.getId() == v.getId()) throw new Erreur(Erreur.EXISTE);
		villes.add(ville);
	}
	
	public void addGare(Gare gare) throws Erreur{
		for(Gare g : gares)
			if(gare.getId() == g.getId()) throw new Erreur(Erreur.EXISTE);
		gares.add(gare);
	}
	
	public void addTrain(Train train) throws Erreur{
		for(Train t : trains)
			if(train.getId() == t.getId()) throw new Erreur(Erreur.EXISTE);
		trains.add(train);
	}
	
	public ArrayList<Train> getTrains(){
		return trains;
	}
	
	// Utilitaires
	/**
	 * Renvois le client associé au login et motDePasse donnés,
	 * Renvois null si aucun client est trouvé
	 */
	public Client findClient(String login, String motDePasse){
		for(Client c : clients){
			if(c.getLogin().compareTo(login) == 0)
				if(c.getMotDePasse().compareTo(motDePasse) == 0)
					return c;
		}
		return null;
	}
	
	public Gare[] getGaresAlph(){
		gares.sort(null);
		Gare[] res = new Gare[gares.size()];
		for( int i=0;i<res.length;i++){
			res[i]=gares.get(i);
		}
		return res;
	}
	
	// Ecriture / lecture
	
	@SuppressWarnings("unchecked")
	public void charger(String fichier){
	    YamlReader reader;
	    ArrayList<Client> nClients = new ArrayList<Client>();
	    ArrayList<Ville> nVilles = new ArrayList<Ville>();
	    ArrayList<Gare> nGares = new ArrayList<Gare>();
	    ArrayList<Train> nTrains = new ArrayList<Train>();
		try {
			reader = new YamlReader(new FileReader("database.yml"));
			nClients = reader.read(nClients.getClass());
			nVilles = reader.read(nVilles.getClass());
			nGares = reader.read(nGares.getClass());
			nTrains = reader.read(nTrains.getClass());
			System.out.println("Chargement de la base de donnée effectuée");
			clients = nClients;
			villes = nVilles;
			gares = nGares;
			trains = nTrains;
		} catch (FileNotFoundException | YamlException e1) {
			e1.printStackTrace();
		}
	}
	
	public void sauvegarder(String fichier){
	    YamlWriter test = null;
		try {
			test = new YamlWriter(new FileWriter(fichier));
			test.write(clients);
			test.write(villes);
			test.write(gares);
			test.write(trains);
			test.close();
			System.out.println("Sauvegarde de la base de donnée effectuée");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
