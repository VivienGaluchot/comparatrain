package defaut;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import modele.Gare;
import modele.Train;
import modele.Ville;

import yamlbeans.YamlException;
import yamlbeans.YamlReader;
import yamlbeans.YamlWriter;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Gestion des données, enregistrements
 */
public class Donnees {
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
	
	public ArrayList<Ville> getVilles(){
		return villes;
	}
	
	public ArrayList<Gare> getGares(){
		return gares;
	}
	
	public ArrayList<Train> getTrains(){
		return trains;
	}
	
	public String[] getGaresAlph(){
		gares.sort(null);
		String[] res = new String[gares.size()];
		for( int i=0;i<res.length;i++){
			res[i]=gares.get(i).toString();
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public void chargerYaml(String fichier){
	    YamlReader reader;
	    ArrayList<Ville> nVilles = new ArrayList<Ville>();
	    ArrayList<Gare> nGares = new ArrayList<Gare>();
	    ArrayList<Train> nTrains = new ArrayList<Train>();
		try {
			reader = new YamlReader(new FileReader("database.yml"));
			nVilles = reader.read(nVilles.getClass());
			nGares = reader.read(nGares.getClass());
			nTrains = reader.read(nTrains.getClass());
			System.out.println("Chargement de la base de donnée effectuée");
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
