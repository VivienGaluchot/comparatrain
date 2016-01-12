package modele;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Resultat extends TreeMap<Double,List<Segment>>{
	private static final long serialVersionUID = 1L;
	
	public void ajouter(Segment t){
		List<Segment> l;
		if(containsKey(t.eval))
			l = get(t.eval);
		else
			l = new ArrayList<Segment>();
		l.add(t);
		put(t.eval, l);
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