/**
 * 
 */
package comparatrain;

import java.util.ArrayList;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Comparateur {
	
	ArrayList<Train> ListeTrain;
	
	public Comparateur(){
		ListeTrain=new ArrayList<Train>();
	}
	
	public void ajoutertrain(Train t){
		ListeTrain.add(t);
	}
	
}
