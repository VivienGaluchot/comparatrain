package donnée;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import comparaison.Comparateur;
import defaut.Erreur;
import elements.Gare;
import elements.Ville;
import train.Train;
import utilisateur.Client;

import yamlbeans.YamlException;
import yamlbeans.YamlReader;
import yamlbeans.YamlWriter;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Gestion des données, enregistrements
 */
public class Donnees {
	// Singleton
	private static volatile Donnees instance = null;
	
	private static String fichier = "database.yml";
		
	private ArrayList<Client> clients;
	private ArrayList<Ville> villes;
	private ArrayList<Gare> gares;
	private ArrayList<Train> trains;
	
	private Donnees(){
		clients = new ArrayList<Client>();
		villes = new ArrayList<Ville>();
		gares = new ArrayList<Gare>();
		trains = new ArrayList<Train>();
	}
	
	public final static Donnees getInstance() {
        if (Donnees.instance == null) {
           synchronized(Comparateur.class) {
             if (Donnees.instance == null) {
            	 Donnees.instance = new Donnees();
             }
           }
        }
        return Donnees.instance;
    }
	
	public void afficher(){
		for(Ville v : villes){
			System.out.println(v);
		}
		System.out.println("---");
		for(Gare g : gares){
			System.out.println(g);
		}
		System.out.println("---");
		for(Train t : getTrains()){
			System.out.println(t);
		}
	}
	
	// Utilitaires
	public void addClient(Client client) throws Erreur{
		for(Client c : clients)
			if(client.equals(c)) throw new Erreur(Erreur.EXISTE);
		clients.add(client);
	}
	
	public void removeClient(Client client){
		clients.remove(client);
	}
	
	public void addVille(Ville ville) throws Erreur{
		for(Ville v : villes)
			if(ville.equals(v)) throw new Erreur(Erreur.EXISTE);
		villes.add(ville);
	}
	
	public void removeVille(Ville ville){
		villes.remove(ville);
	}
	
	public void addGare(Gare gare) throws Erreur{
		for(Gare g : gares)
			if(gare.equals(g)) throw new Erreur(Erreur.EXISTE);
		gares.add(gare);
	}
	
	public void removeGare(Gare gare){
		gares.remove(gare);
	}
	
	public void addTrain(Train train) throws Erreur{
		for(Train t : trains)
			if(train.equals(t)) throw new Erreur(Erreur.EXISTE);
		trains.add(train);
	}
	
	public void removeTrain(Train train){
		trains.remove(train);
	}
	
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
	
	public ArrayList<Gare> getGares(String gare){
		ArrayList<Gare> res = new ArrayList<Gare>();
		for(Gare g : gares){
			if(g.eval(gare)>0) res.add(g);
		}
		return res;
	}
	
	// Getters
	
	public List<Gare> getGares(){
		return Collections.unmodifiableList(gares);
	}
	
	public List<Ville> getVilles(){
		return Collections.unmodifiableList(villes);
	}
	
	public List<Train> getTrains(){
		return Collections.unmodifiableList(trains);
	}
	
	public List<Client> getClients(){
		return Collections.unmodifiableList(clients);
	}
	
	public Gare getGare(int id){
		for(Gare g : gares)
			if(g.getId() == id)
				return g;
		return null;
	}
	
	public Ville getVille(int id){
		for(Ville v : villes)
			if(v.getId() == id)
				return v;
		return null;
	}
	
	public Train getTrain(int id){
		for(Train t : trains)
			if(t.getId() == id)
				return t;
		return null;
	}
	
	
	// Sauvegarde - Chargement
	
	public static void setFichier(String fichier){
		Donnees.getInstance();
		Donnees.fichier = fichier;
	}
	
	public static final void sauvegarder(){
		Donnees.getInstance();
		Donnees.getInstance().sauvegarder(fichier);
	}
	
	public static final void charger(){
		Donnees.getInstance();
		Donnees.getInstance().charger(fichier);
	}
	
	@SuppressWarnings("unchecked")
	public void charger(String fichier){
	    YamlReader reader;
	    ArrayList<Client> nClients;
	    ArrayList<Ville> nVilles;
	    ArrayList<Gare> nGares;
	    ArrayList<Train> nTrains;
		try {
			reader = new YamlReader(new FileReader(fichier));
			nClients = reader.read(clients.getClass());
			nVilles = reader.read(villes.getClass());
			nGares = reader.read(gares.getClass());
			nTrains = reader.read(trains.getClass());
			System.out.println("Chargement de la base de donnée effectuée :");
			System.out.print(nClients.size() + " clients, ");
			System.out.print(nVilles.size() + " villes, ");
			System.out.print(nGares.size() + " gares, ");
			System.out.println(nTrains.size() + " trains");
			clients = nClients;
			villes = nVilles;
			gares = nGares;
			trains = nTrains;
		} catch (FileNotFoundException | YamlException | NullPointerException e1) {
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
