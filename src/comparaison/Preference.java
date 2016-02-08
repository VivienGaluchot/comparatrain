package comparaison;

import defaut.Erreur;
import elements.Horaire;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Preference {
	private String gDepart;
	private String gArrivee;
	
	private Horaire hDepart;
	private Horaire hArrivee;
	
	// Optionel
	private Boolean direct = null;
	Integer nombre = null;
	Integer classe = null;
	
	public Preference(){
		gDepart = null;
		gArrivee = null;
		hDepart = null;
		hArrivee = null;
	}
	
	public Preference(String d, String hD, String a, String hA){
		setGDepart(d);
		setGArrivee(a);
		try {
			setHDepart(new Horaire(hD));
			setHArrivee(new Horaire(hA));
		} catch (Erreur e) {
			System.out.println(e);
		}
	}
	
	// Gares
	public void setGares(String d, String a){
		setGDepart(d);
		setGArrivee(a);
	}
	
	public String getGDepart() {
		return gDepart;
	}

	public void setGDepart(String gDepart) {
		this.gDepart = gDepart;
	}

	public String getGArrivee() {
		return gArrivee;
	}

	public void setGArrivee(String gArrivee) {
		this.gArrivee = gArrivee;
	}
	
	// Horaires
	public void setHArrivee(String hA){
		try {
			setHArrivee(new Horaire(hA));
		} catch (Erreur e) {
			System.out.println(e);
		}
	}
	
	public void setHDepart(String hD){
		try {
			setHDepart(new Horaire(hD));
		} catch (Erreur e) {
			System.out.println(e);
		}
	}
	
	public Horaire getHDepart() {
		return hDepart;
	}

	public void setHDepart(Horaire hDepart) {
		this.hDepart = hDepart;
	}

	public Horaire getHArrivee() {
		return hArrivee;
	}

	public void setHArrivee(Horaire hArrivee) {
		this.hArrivee = hArrivee;
	}

	public Boolean getDirect() {
		return direct;
	}

	public void setDirect(Boolean direct) {
		this.direct = direct;
	}
}
