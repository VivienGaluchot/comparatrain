package comparaison;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

public class Resultat extends TreeSet<Offre>{
	private static final long serialVersionUID = 1L;
	
	public void ajouter(Offre t){
		add(t);
	}
	
	public void afficher(){
		if(this.isEmpty() || last().eval == 0) System.out.println("Aucun résultat");
		else{
			Iterator<Offre> it = descendingIterator();
			while(it.hasNext())
				System.out.println(it.next());		
		}
	}
	
	public ArrayList<Offre> getMeilleurs(int n){
		ArrayList<Offre> res = new ArrayList<Offre>();
		
		if(this.isEmpty()) return res;
		
		int i = 0;
		do{
			Offre t = pollLast();
			res.add(t);
		}while(!isEmpty() && i<n);	
		
		return res;
	}
}