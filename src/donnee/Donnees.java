package donnee;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import comparaison.Comparateur;
import elements.Billet;
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
		
	public static Structure<Client> clients;
	public static Structure<Ville> villes;
	public static Structure<Gare> gares;
	public static Structure<Train> trains;
	public static Structure<Billet> billets; 
	
	private Donnees(){
		clients = new Structure<Client>();
		villes = new Structure<Ville>();
		gares = new Structure<Gare>();
		trains = new Structure<Train>();
		billets = new Structure<Billet>();
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
		System.out.print(clients.size() + " clients, ");
		System.out.print(villes.size() + " villes, ");
		System.out.print(gares.size() + " gares, ");
		System.out.print(trains.size() + " trains,");
		System.out.println(billets.size() + " billets");
	}
	
	// Utilitaires	
	public Gare[] getGaresAlph(){
		gares.getElements().sort(null);
		Gare[] res = new Gare[gares.size()];
		for( int i=0;i<res.length;i++){
			res[i]=gares.get(i);
		}
		return res;
	}
	
	public ArrayList<Gare> getGares(String gare){
		ArrayList<Gare> res = new ArrayList<Gare>();
		for(Gare g : gares.getElements()){
			if(g.eval(gare)>0) res.add(g);
		}
		return res;
	}
	
	public Client findClient(String login, String motDePasse){
		for(Client c : clients.getElements()){
			if(c.getLogin().compareTo(login) == 0)
				if(c.getMotDePasse().compareTo(motDePasse) == 0)
					return c;
		}
		return null;
	}
	
	public static List<Client> getClients(){
		return clients.getElements();
	}
	public static List<Ville> getVilles(){
		return villes.getElements();
	}
	public static List<Gare> getGares(){
		return gares.getElements();
	}
	public static List<Train> getTrains(){
		return trains.getElements();
	}
	public static List<Billet> getBillets(){
		return billets.getElements();
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
	    ArrayList<Client> nClients = null;
	    ArrayList<Ville> nVilles = null;
	    ArrayList<Gare> nGares;
	    ArrayList<Train> nTrains;
		try {
			reader = new YamlReader(new FileReader(fichier));
			nClients = reader.read(ArrayList.class);
			nVilles = reader.read(ArrayList.class);
			nGares = reader.read(ArrayList.class);
			nTrains = reader.read(ArrayList.class);
			clients.setElements(nClients);
			villes.setElements(nVilles);
			gares.setElements(nGares);
			trains.setElements(nTrains);
			System.out.println("Chargement de la base de donnée effectuée :");
			afficher();
		} catch (FileNotFoundException | YamlException | NullPointerException e1) {
			e1.printStackTrace();
		}
	}
	
	public void sauvegarder(String fichier){
	    YamlWriter test = null;
		try {
			test = new YamlWriter(new FileWriter(fichier));
			test.write(clients.getElements());
			test.write(villes.getElements());
			test.write(gares.getElements());
			test.write(trains.getElements());
			test.close();
			System.out.println("Sauvegarde de la base de donnée effectuée");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
