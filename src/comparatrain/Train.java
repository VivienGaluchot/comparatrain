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

	public int eval(Preference pref) {
		return 0;
	}
}
