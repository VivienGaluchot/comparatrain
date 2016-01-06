package comparatrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Gestion des données, enregistrements
 */
public class Donnees {
	ArrayList<Ville> villes;
	ArrayList<Gare> gares;
	ArrayList<Train> trains;
	
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
		for(Train t : trains){
			System.out.println(t);
		}
	}
	
	public boolean charger(String fichier){
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(fichier));
			
			String s;
			boolean villeState = false;
			boolean trainState = false;
			Ville currentVille = null;
			int currentTrainId = 0;
			String currentJour = null;
			ArrayList<String> trajet = null;
			
			while(in.ready()){
				//lecture d'une ligne
				s = in.readLine();
				
				// Nouvelle ville
				if(s.startsWith("--Ville")){
					villeState = false;
					if(trainState){
						trains.add(creerTrain(currentTrainId,currentJour,trajet));
					}
					trainState = false;
					s = s.substring(s.indexOf(':')+2);
					if(s.length() > 0){
						currentVille = new Ville(villes.size(),s);
						villes.add(currentVille);
						villeState = true;
					}
				}
				// Nouveau train
				else if(s.startsWith("--Train")){
					villeState = false;
					if(trainState){
						trains.add(creerTrain(currentTrainId,currentJour,trajet));
					}
					trainState = false;
					s = s.substring(s.indexOf(':')+2);
					if(s.length() > 0){
						currentTrainId = Integer.parseInt(s);
						currentJour = null;
						trajet = new ArrayList<String>();
						trainState = true;
					}
				}
				else if(villeState){
					// Ajout d'une gare dans ville
					if(s.length()>0){
						gares.add(new Gare(gares.size(),s,currentVille));
					}
					else
						villeState = false;
				}
				else if(trainState){
					if(s.startsWith("--Jour")){
						s = s.substring(s.indexOf(':')+2);
						if(s.length() > 0)
							currentJour = s;
						else
							trainState = false;
					}
					else{
						if(s.length() > 0)
							trajet.add(s);
					}
				}
			}
			if(trainState){
				trains.add(creerTrain(currentTrainId,currentJour,trajet));
			}
			
			in.close();
		}
		catch (IOException e) {
			System.out.println("Fichier non trouvé");
			return false;
		}
		return true;
	}
	
	Train creerTrain(int id, String jour, ArrayList<String> strTrajet){
		Trajet trajet = new Trajet();
		
		for(String s : strTrajet){
			String sGare = null;
			Integer idGare = null;
			String h1 = null;
			String h2 = null;
			
			sGare = s.substring(0, s.indexOf(":")-1);
			for(Gare g : gares){
				if(g.nom.compareTo(sGare) == 0){
					idGare = g.id;
					break;
				}
			}
			
			if(idGare == null) return null;
			
			s = s.substring(s.indexOf(':')+2);
			String[] temp = s.split(" ");
			if(temp.length == 1){
				h1 = temp[0];
				if(trajet.depart == null){
					trajet.depart = new Depart(gares.get(idGare),new Horaire(jour + " " + h1));
				}
				else if(trajet.arrive == null){
					trajet.arrive = new Arrive(gares.get(idGare),new Horaire(jour + " " + h1));
				}
				else{
					return null;
				}
			}
			else if(temp.length == 2 && trajet.arrive == null){
				h1 = temp[0];
				h2 = temp[1];
				
				trajet.escales.add(new Escale(gares.get(idGare), new Horaire(jour + " " + h1), new Horaire(jour + " " + h2)));
			}
		}
		
		try {
			return new Train(id,trajet);
		} catch (Erreur e) {
			System.out.println("Erreur de chargement");
			return null;
		}
	}
	
	boolean sauvegarder(String fichier){
		File file =new File(fichier);
        try {
			file .createNewFile();
			FileWriter writer = new FileWriter(fichier);
			try {
				for(Ville v : villes){
					writer.write("--Ville : " + v.nom + "\n");
					for(Gare g : gares){
						if(g.ville.id == v.id){
							writer.write(g.nom + "\n");
						}
					}
				}
				writer.write("\n");
				for(Train t : trains){
					writer.write("--Train : " + t.id + "\n");
					writer.write("--Jour : " + t.t.depart.h.jourToString() + "\n");
					writer.write(t.t.depart.g.nom + " : " + t.t.depart.h.heureToString() + "\n");
					for(Escale e : t.t.escales){
						writer.write(e.g.nom + " : " + e.hA.heureToString() + " " + e.hD.heureToString() + "\n");
					}
					writer.write(t.t.arrive.g.nom + " : " + t.t.arrive.h.heureToString() + "\n");
				}
				writer.write("\n");
			} finally {
			    writer.close();
			}
        } catch (Exception e) {
            System.out.println("Impossible de creer le fichier");
        }
		return false;
	}
}
