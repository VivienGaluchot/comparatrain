package comparatrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Resultat extends TreeMap<Double,List<Segment>>{
	private static final long serialVersionUID = 1L;
	
	public void ajouter(Double e, Segment t){
		List<Segment> l;
		if(containsKey(e))
			l = get(e);
		else
			l = new ArrayList<Segment>();
		l.add(t);
		put(e, l);
	}
	
	public void afficher(){
		List<Segment> l;
		if(this.isEmpty()) System.out.println("Aucun résultat");
		else if(lastKey() > 0){
			Map.Entry<Double, List<Segment>> ent;
			do{
				ent = pollLastEntry();
				l = ent.getValue();
				for(Segment t : l)
					System.out.println(t);
			}while(!isEmpty() && lastKey() > 0);			
		}
	}
}

class Segment implements Evaluable{
	Gare d;
	Gare a;
	Horaire hD;
	Horaire hA;
	
	Train t;
	
	Double eval;
	
	public Segment(Train train){
		t = train;
	}
	
	public void set(Gare depart, Gare arrive, Horaire hDepart, Horaire hArrive){
		a = arrive;
		d = depart;
		hD = hDepart;
		hA = hArrive;
	}
	
	public double eval(Preference pref) {
		double res = 1;
		
		// Lieux
		res *= d.eval(pref.gDepart);
		if(res == 0) return res;
		res *= a.eval(pref.gArrive);
		if(res == 0) return res;
		
		// Dates
		if(pref.hDepart != null)
			res *= hD.eval(pref.hDepart);
		if(res == 0) return res;
		if(pref.hArrive != null)
			res *= hA.eval(pref.hArrive);
		
		eval = res;
		
		return res;
	}
	
	public Segment clone(){
		Segment s = new Segment(t);
		s.set(d, a, hD, hA);
		s.eval = eval;
		return s;
	}
	
	/**
	 * Affichage d'un segment
	 */
	public String toString(){
		return "Train n°" + t.id + " : " + d + " " + hD + " --> " + a + " " + hA;
	}
}