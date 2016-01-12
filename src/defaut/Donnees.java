package defaut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import yamlbeans.YamlWriter;

import modele.Gare;
import modele.Horaire;
import modele.Train;
import modele.Trajet;
import modele.Trajet.Escale;
import modele.Ville;

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
	
	public void charger(String fichier){
		BufferedReader in;
		int ligne = 0;
		
		try {
			String s;
			boolean villeState = false;
			boolean trainState = false;
			Ville currentVille = null;
			int currentTrainId = 0;
			ArrayList<String> trajet = null;
			
			in = new BufferedReader(new FileReader(fichier));
			try {
				while(in.ready()){
					//lecture d'une ligne
					s = in.readLine();
					ligne++;
					
					// Commmentaire
					if(s.startsWith("#")){
					}
					// Nouvelle ville
					else if(s.startsWith("--Ville")){
						villeState = false;
						if(trainState){
							getTrains().add(creerTrain(currentTrainId,trajet));
						}
						trainState = false;
						s = s.substring(s.indexOf(':')+2);
						if(s.length() > 0){
							currentVille = new Ville(villes.size(),s);
							villes.add(currentVille);
							villeState = true;
						}
						else throw new Erreur(2);
					}
					// Nouveau train
					else if(s.startsWith("--Train")){
						villeState = false;
						if(trainState){
							getTrains().add(creerTrain(currentTrainId,trajet));
						}
						trainState = false;
						s = s.substring(s.indexOf(':')+2);
						if(s.length() > 0){
							currentTrainId = Integer.parseInt(s);
							trajet = new ArrayList<String>();
							trainState = true;
						}
						else throw new Erreur(2);
					}
					else if(villeState){
						// Ajout d'une gare dans ville
						if(s.length()>0)
							gares.add(new Gare(gares.size(),s,currentVille));
					}
					else if(trainState){
						if(s.length() > 0)
							trajet.add(s);
					}
				}
				if(trainState){
					getTrains().add(creerTrain(currentTrainId,trajet));
				}
			} catch (Erreur e) {
				System.out.println(e + " - ligne " + ligne);
			} finally {
				in.close();
			}
		}
		catch (IOException e) {
			System.out.println("Fichier non trouvé");
		}
	}
	
	public void sauvegarder(){
	    YamlWriter test;
		try {
			test = new YamlWriter(new FileWriter("Villes.yml"));
		    test.write(villes);
		    test.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			test = new YamlWriter(new FileWriter("Gares.yml"));
		    test.write(gares);
		    test.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			test = new YamlWriter(new FileWriter("Trains.yml"));
		    test.write(trains);
		    test.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	Train creerTrain(int id, ArrayList<String> strTrajet) throws Erreur{
		Trajet trajet = new Trajet();
		
		for(String s : strTrajet){
			String sGare = null;
			Integer idGare = null;
			
			sGare = s.substring(0, s.indexOf(":")-1);
			for(Gare g : gares){
				if(g.getNom().compareTo(sGare) == 0){
					idGare = g.getId();
					break;
				}
			}
			
			if(idGare == null) throw new Erreur(3);
			
			s = s.substring(s.indexOf(':')+2);
			String[] temp = s.split(" ");
			if(temp.length == 2){
				if(trajet.getDepart() == null){
					trajet.setDepart(gares.get(idGare),new Horaire(temp[0] + " " + temp[1]));
				}
				else if(trajet.getArrivee() == null){
					trajet.setArrivee(gares.get(idGare),new Horaire(temp[0] + " " + temp[1]));
				}
				else{
					throw new Erreur(3);
				}
			}
			else if(temp.length == 4 && trajet.getArrivee() == null){
				trajet.addEscale(gares.get(idGare), new Horaire(temp[0] + " " + temp[1]), new Horaire(temp[2] + " " + temp[3]));
			}
			else throw new Erreur(3);
		}
	
		return new Train(id,trajet);
	}
}
