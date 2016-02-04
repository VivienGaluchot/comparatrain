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
	// Singleton
	private static volatile Comparateur instance = null;
	
	private Donnees data = null;
	private static GrapheCorrespondances graph = null;
	
	private Comparateur(){
	}
	
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
		if(graph == null) return null;
		
		Resultat resultat = new Resultat();
		
		List<Offre> offres = graph.trouverOffre(pref);
		
		for(Offre o : offres){
			if(o.eval(pref) > 0)
				resultat.add(o);
		}
		
		return resultat;
	}
	
	public void setData(Donnees d){
		data = d;
		graph = new GrapheCorrespondances(data.getTrains());
	}
	
	public Donnees getData(){
		return data;
	}
}
