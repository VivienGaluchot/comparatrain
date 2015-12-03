/**
 * 
 */
package comparatrain;

import java.time.LocalDateTime;
import java.util.ArrayDeque;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 * Classe de train
 */
public class Train implements Evaluable{
	int id;
	Gare gDepart;
	Gare gArrive;
	LocalDateTime  tDepart;
	LocalDateTime tArrive;
	
	ArrayDeque<Siege> places;
	
	public Train(int i,Gare gDep,Gare gArr,LocalDateTime tDep,LocalDateTime tArr){
		id = i;
		gDepart = gDep;
		gArrive = gArr;
		tDepart = tDep;
		tArrive = tArr;
		for(int j=0;j<50;j++)
			places.push(new Siege(j));
	}
	
	/**
	 * Par rapport aux préférences :
	 * gare de départ identique +1000
	 * gare d'arrivé identique  +1000
	 * si jour de depart identique
	 *    à 0h d'ecart          +1000
	 *    à 5h d'ecart          +0
	 * si jour d'arrivé identique
	 *    à 0h d'ecart          +1000
	 *    à 5h d'ecart          +0
	 */
	public int eval(Preference pref) {
		int res = 0;
		int t;
		
		// Lieux
		if(gDepart == pref.gDepart)  res += 1000;
		if(gArrive == pref.gArrive)  res += 1000;
		
		// Dates
		if(tDepart.toLocalDate().compareTo(pref.tDepart.toLocalDate()) == 0 ){
			//à 0h : +1000, à 5h : +0
			t = tDepart.toLocalTime().toSecondOfDay() - pref.tDepart.toLocalTime().toSecondOfDay();
			t = t/18; //18000s = 5h, à 0h : t=0; à 5h : t=1000
			if(t<0) t=-t;
			res = 1000 - Math.min(t, 1000); 
		}
		if(tArrive.toLocalDate().compareTo(pref.tArrive.toLocalDate()) == 0 ){
			//à 0h : +1000, à 5h : +0
			t = tArrive.toLocalTime().toSecondOfDay() - pref.tArrive.toLocalTime().toSecondOfDay();
			t = t/18; //18000s = 5h, à 0h : t=0; à 5h : t=1000
			if(t<0) t=-t;
			res = 1000 - Math.min(t, 1000); 
		}
		
		return res;
	}
}
