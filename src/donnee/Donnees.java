package donnee;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import elements.Gare;
import elements.Ville;
import offre.Billet;
import train.Rame;
import train.Train;
import utilisateur.Client;

import yamlbeans.YamlException;
import yamlbeans.YamlReader;
import yamlbeans.YamlWriter;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Gestion des données, chargements et enregistrements
 */
public class Donnees {
	private static volatile Donnees instance = null;
	
	private static String fichier = "database.yml";
		
	public static Structure<Client> clients;
	public static Structure<Ville> villes;
	public static Structure<Gare> gares;
	public static Structure<Rame> rames;
	public static Structure<Train> trains;
	public static Structure<Billet> billets;
	
	private Donnees(){
		clients = new Structure<Client>();
		villes = new Structure<Ville>();
		gares = new Structure<Gare>();
		rames = new Structure<Rame>();
		trains = new Structure<Train>();
		billets = new Structure<Billet>();
	}
	
	public final static Donnees getInstance() {
        if (Donnees.instance == null) {
           synchronized(Donnees.class) {
             if (Donnees.instance == null) {
            	 Donnees.instance = new Donnees();
             }
           }
        }
        return Donnees.instance;
    }
	
	public void afficher(){
		System.out.println(clients.size() + " clients");
		System.out.println(villes.size() + " villes");
		System.out.println(gares.size() + " gares");
		System.out.println(rames.size() + " rames");
		System.out.println(trains.size() + " trains");
		System.out.println(billets.size() + " billets");
	}
	
	// Utilitaires
	public static Ville[] getVillesAlph(){
		ArrayList<Ville> temp = new ArrayList<Ville>();
		temp.addAll(villes.getElements());
		Ville[] res = new Ville[temp.size()];
		for( int i=0;i<res.length;i++){
			res[i]=temp.get(i);
		}
		return res;
	}
	
	public static Gare[] getGaresAlph(){
		ArrayList<Gare> temp = new ArrayList<Gare>();
		temp.addAll(gares.getElements());
		temp.sort(null);
		Gare[] res = new Gare[temp.size()];
		for( int i=0;i<res.length;i++){
			res[i]=temp.get(i);
		}
		return res;
	}
	
	public static Rame[] getRamesT(){
		ArrayList<Rame> temp = new ArrayList<Rame>();
		temp.addAll(rames.getElements());
		temp.sort(null);
		Rame[] res = new Rame[temp.size()];
		for( int i=0;i<res.length;i++){
			res[i]=temp.get(i);
		}
		return res;
	}
	
	public static List<String> getStrLieux(){
		ArrayList<String> res= new ArrayList<String>();
		for(Gare g : getGares()){
			res.add(g.getNom());
		}
		for(Ville v : getVilles()){
			res.add(v.getNom());
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
	public static List<Rame> getRames(){
		return rames.getElements();
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
	    ArrayList<Client> nClients;
	    ArrayList<Ville> nVilles;
	    ArrayList<Gare> nGares;
	    ArrayList<Rame> nRames;
	    ArrayList<Train> nTrains;
	    ArrayList<Billet> nBillets;
		try {
			reader = new YamlReader(new FileReader(fichier));
			nClients = reader.read(ArrayList.class);
			nVilles = reader.read(ArrayList.class);
			nGares = reader.read(ArrayList.class);
			nRames = reader.read(ArrayList.class);
			nTrains = reader.read(ArrayList.class);
			nBillets = reader.read(ArrayList.class);
			if(nClients != null)
				clients.setElements(nClients);
			if(nVilles != null)
				villes.setElements(nVilles);
			if(nGares != null)
				gares.setElements(nGares);
			if(nRames != null)
				rames.setElements(nRames);
			if(nTrains != null)
				trains.setElements(nTrains);
			if(nBillets != null)
				billets.setElements(nBillets);
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
			test.write(rames.getElements());
			test.write(trains.getElements());
			test.write(billets.getElements());
			test.close();
			System.out.println("Sauvegarde de la base de donnée effectuée");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
