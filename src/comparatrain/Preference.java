/**
 * 
 */
package comparatrain;

import java.time.LocalDateTime;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Preference {
	Gare gDepart;
	Gare gArrive;
	LocalDateTime  tDepart;
	LocalDateTime tArrive;
	
	public Preference (Gare d, Gare a, LocalDateTime ti, LocalDateTime tf){
		gDepart=d;
		gArrive=a;
		tDepart=ti;
		tArrive=tf;
	}
}
