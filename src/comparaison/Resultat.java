package comparaison;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Resultat extends TreeMap<Double,List<Offre>>{
	private static final long serialVersionUID = 1L;
	
	public void ajouter(Offre t){
		List<Offre> l;
		if(containsKey(t.eval))
			l = get(t.eval);
		else
			l = new ArrayList<Offre>();
		l.add(t);
		put(t.eval, l);
	}
	
	public void afficher(){
		List<Offre> l;
		if(this.isEmpty() || lastKey() <= 0) System.out.println("Aucun résultat");
		else{
			Map.Entry<Double, List<Offre>> ent;
			do{
				ent = pollLastEntry();
				l = ent.getValue();
				for(Offre t : l)
					System.out.println(t);
			}while(!isEmpty() && lastKey() > 0);			
		}
	}
	
	public ArrayList<Offre> getMeilleurs(int n){
		ArrayList<Offre> res = new ArrayList<Offre>();
		
		// Si arbre vide : fin
		if(this.isEmpty() || lastKey() <= 0) return res;
		
		List<Offre> l;
		Map.Entry<Double, List<Offre>> ent;
		int i = 0;
		do{
			ent = pollLastEntry();
			l = ent.getValue();
			for(int j=0;j<l.size() && i<n; j++){
				res.add(l.get(j));
				i++;
			}
		}while(!isEmpty() && lastKey() > 0 && i<n);	
		
		return res;
	}
}