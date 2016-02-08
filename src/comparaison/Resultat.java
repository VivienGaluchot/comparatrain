package comparaison;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import elements.Offre;

public class Resultat extends TreeSet<Offre>{
	private static final long serialVersionUID = 1L;
	
	public void afficher(){
		if(this.isEmpty() || last().getEval() == 0) System.out.println("Aucun résultat");
		else{
			Iterator<Offre> it = descendingIterator();
			int i = 1;
			while(it.hasNext())
				System.out.println("Billet " + (i++) + "\n" + it.next());		
		}
	}
	
	public List<Offre> getMeilleurs(int n){
		ArrayList<Offre> res = new ArrayList<Offre>();
		
		if(this.isEmpty()) return res;
		
		int i = 0;
		Iterator<Offre> it = descendingIterator();
		while(it.hasNext() && i++<n)
			res.add(it.next());	
		
		return res;
	}
}