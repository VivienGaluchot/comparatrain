/**
 * 
 */
package comparaison;

import java.util.List;

import donnee.Donnees;
import elements.Offre;

/**
 * @author Vivien Galuchot - Vincent Hernandez
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
	
	public final static Resultat comparer(Preference pref){
		if(graph == null) buildGraph();		
		List<Offre> offres = graph.trouverOffre(pref);
		
		Resultat resultat = new Resultat();
		for(Offre o : offres){
			if(o.eval(pref) > 0)
				resultat.add(o);
		}
		
		return resultat;
	}
	
	public final static void buildGraph(){
		graph = new GrapheCorrespondances(Donnees.getTrains());
	}
}
