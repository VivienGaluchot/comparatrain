/**
 * 
 */
package comparatrain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Objet evaluant les offres
 */
public class Comparateur {
	
	ArrayList<Train> listeTrain;
	
	public Comparateur(){
		listeTrain = new ArrayList<Train>();
	}
	
	protected void ajouterTrain(Gare gDepart, Gare gArrive, Horaire hDepart, Horaire hArrive){
		Train t;
		try {
			t = new Train(listeTrain.size(), gDepart, gArrive, hDepart, hArrive);
			listeTrain.add(t);
		} catch (Erreur e) {
			System.out.println("Erreur : " + e);
		}
	}
	
	/**
	 * @param pref : Preference avec lequeles effectuer la comparaison des offres
	 * Tout les trains de la liste ListeTrain sont évalués, les scores et les trains sont stoqués dans une TreeMap
	 */
	public void comparer(Preference pref){
		TreeMap<Double,Train> evaluations = new TreeMap<Double,Train>();
		
		Double e;
		for(Train t : listeTrain){
			e = t.eval(pref);
			evaluations.put(e, t);
			if(e!=0){
				System.out.println(t.id + " : " + e);
				System.out.println(t);
			}
		}
		
// Marche pas, comprends pas pourquoi
//
//		if(evaluations.lastKey() > 0){
//			Map.Entry<Double, Train> ent;
//			ent = evaluations.pollLastEntry();
//			System.out.println(ent.getValue());
//			while(ent.getKey() > 0){
//				ent = evaluations.pollLastEntry();
//				System.out.println(ent.getValue());
//			}
//		}
	}
	
	public void ajouterOffresTest(){
		Ville paris = new Ville(0,"Paris");
		Ville marseille = new Ville(1,"Marseille");
		Ville lyon = new Ville(2,"Lyon");
		Ville nantes = new Ville(3,"Nantes");
		Ville avignon = new Ville(4,"Avignon");
		
		Gare G1 = new Gare(0,"Gare de Lyon",paris);
		Gare G2 = new Gare(1,"Massy TGV",paris);
		Gare G3 = new Gare(2,"Lyon Pardieu",lyon);
		Gare G4 = new Gare(3,"Avignon TGV",avignon);
		Gare G5 = new Gare(4,"Gare de Nantes",nantes);
		Gare G6 = new Gare(5,"Saint Charles",marseille);
		
		Horaire D1 = new Horaire(LocalDateTime.of(2015, 12, 18, 14, 30));
		Horaire D2 = new Horaire(LocalDateTime.of(2015, 12, 18, 17, 30));
		Horaire D3 = new Horaire(LocalDateTime.of(2015, 12, 18, 15, 30));
		Horaire D4 = new Horaire(LocalDateTime.of(2015, 12, 18, 19, 30));
		Horaire D5 = new Horaire(LocalDateTime.of(2015, 12, 18, 20, 30));
		Horaire D6 = new Horaire(LocalDateTime.of(2015, 12, 18, 21, 30));
		Horaire D7 = new Horaire(LocalDateTime.of(2015, 12, 18, 22, 30));
		
		ajouterTrain(G1,G6,D1,D2);
		ajouterTrain(G1,G6,D3,D4);
		ajouterTrain(G6,G1,D1,D2);
		ajouterTrain(G6,G1,D3,D4);
		ajouterTrain(G6,G5,D1,D4);
		ajouterTrain(G6,G5,D2,D5);
		ajouterTrain(G6,G5,D3,D6);
		ajouterTrain(G6,G5,D4,D7);
		ajouterTrain(G5,G6,D2,D5);
	}
}
