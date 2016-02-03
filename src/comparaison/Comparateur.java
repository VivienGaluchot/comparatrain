/**
 * 
 */
package comparaison;

import java.util.List;

import donnée.Donnees;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * utilise le modele pour comparer la base de donnée avec le modele
 */
public class Comparateur {
	
	private Donnees data;
	private GrapheCorrespondances graph;
	
	public Comparateur(){
		data = new Donnees();
	}
	
	public Comparateur(Donnees d){
		data = d;
		graph = new GrapheCorrespondances(data.getTrains());
	}
	
	public Resultat comparer(Preference pref){
		Resultat resultat = new Resultat();
		
		List<Offre> offres = graph.trouverOffre(pref);
		
		for(Offre o : offres){
			if(o.eval(pref) > 0)
				resultat.ajouter(o);
		}
		
		return resultat;
	}
	
	public Donnees getData(){
		return data;
	}
}
