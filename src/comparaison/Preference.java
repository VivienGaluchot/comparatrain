/**
 * 
 */
package comparaison;

import defaut.Erreur;
import modele.Horaire;
import modele.physique.Wagon;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Preference {
	private String gDepart;
	private String gArrive;
	
	private Horaire hDepart;
	private Horaire hArrive;
	
	private Boolean wagonBar = null;
	private Integer classe = null;
	
	public Preference(){
		setgDepart("");
		setgArrive("");
		sethDepart(null);
		sethArrive(null);
	}
	
	public Preference(String d, String hD, String a, String hA){
		setgDepart(d);
		setgArrive(a);
		try {
			sethDepart(new Horaire(hD));
			sethArrive(new Horaire(hA));
		} catch (Erreur e) {
			System.out.println(e);
		}
	}
	
	// Gares
	public void setGares(String d, String a){
		setgDepart(d);
		setgArrive(a);
	}
	
	public String getgDepart() {
		return gDepart;
	}

	public void setgDepart(String gDepart) {
		this.gDepart = gDepart;
	}

	public String getgArrive() {
		return gArrive;
	}

	public void setgArrive(String gArrive) {
		this.gArrive = gArrive;
	}
	
	// Horaires
	public void setHArrive(String hA){
		try {
			sethArrive(new Horaire(hA));
		} catch (Erreur e) {
			System.out.println(e);
		}
	}
	
	public void setHDepart(String hD){
		try {
			sethDepart(new Horaire(hD));
		} catch (Erreur e) {
			System.out.println(e);
		}
	}
	
	public Horaire gethDepart() {
		return hDepart;
	}

	public void sethDepart(Horaire hDepart) {
		this.hDepart = hDepart;
	}

	public Horaire gethArrive() {
		return hArrive;
	}

	public void sethArrive(Horaire hArrive) {
		this.hArrive = hArrive;
	}
	
	// Wagon
	public void setWagonBar(boolean b){
		wagonBar = new Boolean(b);
	}
	
	public void setClasse(int c){
		if(c == Wagon.PREMIERE || c == Wagon.SECONDE){
			classe = c;
		}
	}
}
