/**
 * 
 */
package comparaison;

import java.util.ArrayList;
import java.util.List;

import defaut.Erreur;
import donnée.Donnees;
import elements.Gare;
import elements.GareHoraire;
import elements.Horaire;
import elements.Segment;
import elements.SegmentHoraire;
import train.Train;

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
		buildGraph();
		GareHoraire a;
		GareHoraire b;
		a = data.getTrain(5642).getDepart(); // stcharles
		b = data.getTrain(7010).getArrivee(); // nantes
		
		SegmentHoraire segment = new SegmentHoraire(a,b);
		List<Offre> l = graph.trouverOffre(segment);
		for(Offre o : l)
			System.out.println(o);
	}
	
	public Resultat comparer(Preference pref){
		Resultat resultat = new Resultat();
		
		ArrayList<Offre> e = trouverOffre(pref);
		
		for(Offre o : e){
			if(o.eval(pref) > 0)
				resultat.ajouter(o);
		}
		
		return resultat;
	}
	
	public ArrayList<Offre> trouverOffre(Preference pref) {
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		ArrayList<Gare> garesD = data.getGares(pref.getGDepart());
		ArrayList<Gare> garesA = data.getGares(pref.getGArrivee());
		
		for(Gare gD : garesD)
			for(Gare gA : garesA)
				resultat.addAll(trouverOffre(new Segment(gD,gA),pref.direct));
		
		return resultat;
	}
	
	public ArrayList<Offre> trouverOffre(Segment segment, Boolean direct){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		// OffreSimple		
		for(Train t : data.getTrains()){
			SegmentHoraire s = t.trouver(segment);
			if(s != null) resultat.add(new OffreSimple(t,t.getPlace(),s));
		}
		
		// OffreMultiple
		if(direct==null || !direct){
			// A FAIRE
		}
		
		return resultat;
	}
	
	public void buildGraph(){
		graph = new GrapheCorrespondances();
		for(Train t : data.getTrains()){
			graph.addTrain(t);
		}
		graph.connect();
	}
	
	public Donnees getData(){
		return data;
	}
}
