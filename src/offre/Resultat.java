package offre;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import elements.Evaluable;
import elements.Indexable;
import elements.Preference;
/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Resultat, type de TreeSet contennant des offres
 */
public class Resultat<E extends Evaluable<Preference>>{	
	private Preference pref;
	private TreeSet<E> set;
	
	public Resultat(Preference pref){
		set = new TreeSet<E>(new EvalComp());
		this.pref = pref;
	}
	
	public void afficher(){
		if(set.isEmpty()) System.out.println("Aucun résultat");
		else{
			Iterator<E> it = set.descendingIterator();
			int i = 1;
			while(it.hasNext())
				System.out.println("Billet " + (i++) + "\n" + it.next());		
		}
	}
	
	public void add(E el){
		set.add(el);
	}
	
	public List<E> getMeilleurs(int n){
		ArrayList<E> res = new ArrayList<E>();
		
		if(set.isEmpty()) return res;
		
		int i = 0;
		Iterator<E> it = set.descendingIterator();
		while(it.hasNext() && i++<n)
			res.add(it.next());	
		
		return res;
	}
	
	public Preference getPref() { return pref; }
	public void setPref(Preference pref) { this.pref = pref; }

	private class EvalComp implements Comparator<Evaluable<Preference>>{
		@Override
		public int compare(Evaluable<Preference> o1, Evaluable<Preference> o2) {
			double e1 = o1.eval(pref);
			double e2 = o2.eval(pref);
			if(e1 > e2) return 1;
			if(e1 < e2) return -1;
			if(Indexable.class.isInstance(o1) && Indexable.class.isInstance(o2))
					return ((Indexable)o1).getId() - ((Indexable)o2).getId();
			return 0;
		}
	}
}