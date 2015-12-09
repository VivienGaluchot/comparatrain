/**
 * 
 */
package comparatrain;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Preference {
	protected String gDepart;
	protected String gArrive;
	
	protected Horaire hDepart;
	protected Horaire hArrive;
	
	public Preference(){
		gDepart = "";
		gArrive = "";
		hDepart = null;
		hArrive = null;
	}
	
	public Preference(String d, String hD, String a, String hA){
		gDepart = d;
		gArrive = a;
		hDepart = new Horaire(hD);
		hArrive = new Horaire(hA);
	}
	
	public void setGares(String d, String a){
		gDepart = d;
		gArrive = a;
	}
	
	public void setHArrive(String hA){
		hArrive = new Horaire(hA);
	}
	
	public void setHDepart(String hD){
		hDepart = new Horaire(hD);
	}
}
