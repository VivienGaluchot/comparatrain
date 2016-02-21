/**
 * 
 */
package offre;

import java.util.List;

import elements.Preference;
import train.Train;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Object effectuant la recherche et la comparaison des offres
 * est composé d'un graphe de correspondance
 */
public class Comparateur {
	// Singleton
	private static volatile Comparateur instance = null;
	
	private static GrapheCorrespondances graph = null;
	
	private Comparateur(){}
	
	public final static Comparateur getInstance() {
        if (Comparateur.instance == null) {
           synchronized(Comparateur.class) {
             if (Comparateur.instance == null) {
            	 Comparateur.instance = new Comparateur();
             }
           }
        }
        return Comparateur.instance;
    }
	
	public final static Resultat comparer(Preference pref, List<Billet> list){
		if(graph == null) return null;
		List<Offre> offres = graph.trouverOffre(pref);
		
		Resultat resultat = new Resultat();		
		for(Offre o : offres){
			Billet.chercherBillets(o, pref, list);
			if(o.eval(pref) > 0)
				resultat.add(o);
		}
		
		return resultat;
	}
	
	public final static void buildGraph(List<Train> trains){
		graph = new GrapheCorrespondances(trains);
	}
	
	
}
