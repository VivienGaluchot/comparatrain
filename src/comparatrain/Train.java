/**
 * 
 */
package comparatrain;

import java.time.LocalDateTime;

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
	
	public Train(int i,Gare gDep,Gare gArr,LocalDateTime tDep,LocalDateTime tArr){
		id = i;
		gDepart = gDep;
		gArrive = gArr;
		tDepart = tDep;
		tArrive = tArr;
	}

	public int eval() {
		return 0;
	}
}
