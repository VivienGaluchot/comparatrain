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
		setVilles(new ArrayList<Ville>());
		setGares(new ArrayList<Gare>());
		trains = new ArrayList<Train>();
	}
	
	public void afficher(){
		for(Ville v : getVilles()){
			System.out.println(v);
		}
		System.out.println("---");
		for(Gare g : getGares()){
			System.out.println(g);
		}
		System.out.println("---");
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
		for(Ville v : getVilles())
			if(ville.getId() == v.getId()) throw new Erreur(Erreur.EXISTE);
		getVilles().add(ville);
	}
	
	public void addGare(Gare gare) throws Erreur{
		for(Gare g : getGares())
			if(gare.getId() == g.getId()) throw new Erreur(Erreur.EXISTE);
		getGares().add(gare);
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
		getGares().sort(null);
		Gare[] res = new Gare[getGares().size()];
		for( int i=0;i<res.length;i++){
			res[i]=getGares().get(i);
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
			setVilles(nVilles);
			setGares(nGares);
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
			test.write(getVilles());
			test.write(getGares());
			test.write(trains);
			test.close();
			System.out.println("Sauvegarde de la base de donnée effectuée");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public ArrayList<Ville> getVilles() {
		return villes;
	}

	public void setVilles(ArrayList<Ville> villes) {
		this.villes = villes;
	}

	public ArrayList<Gare> getGares() {
		return gares;
	}

	public void setGares(ArrayList<Gare> gares) {
		this.gares = gares;
	}
}
