package comparatrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Resultat extends TreeMap<Double,List<Train>>{
	private static final long serialVersionUID = 1L;
	
	public void ajouter(Double e, Train t){
		List<Train> l;
		if(containsKey(e))
			l = get(e);
		else
			l = new ArrayList<Train>();
		l.add(t);
		put(e, l);
	}
	
	public void afficher(){
		List<Train> l;
		if(lastKey() > 0){
			Map.Entry<Double, List<Train>> ent;
			do{
				ent = pollLastEntry();
				l = ent.getValue();
				for(Train t : l)
					System.out.println(t);
			}while(!isEmpty() && lastKey() > 0);			
		}
	}
}
