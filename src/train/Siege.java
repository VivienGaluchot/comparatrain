/**
 * 
 */
package train;

import comparaison.Preference;
import elements.Evaluable;
import elements.Indexable;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 */
public class Siege extends Indexable implements Evaluable<Preference>{
	public static final Integer AVANT = 1;
	public static final Integer ARRIERE = 2;
	public static final Integer FENETRE = 3;
	public static final Integer COULOIR = 4;
	
	private Integer sens;
	private Integer cote;
	
	public Siege(){
		sens = null;
		setCote(null);
	}
	
	public Siege(Integer i,Integer sens, Integer cote){
		setId(i);
		this.sens = sens;
		this.setCote(cote);
	}
	
	public Siege(Integer i,Integer sens, Integer cote, boolean occupe){
		setId(i);
		this.sens = sens;
		this.setCote(cote);
	}
	
	public double eval(Preference pref){
		return 1;
	}
	
	public String toString(){
		String res;
		if(sens==AVANT)
			res = "u";
		else
			res = "n";
		return res;
	}

	public Integer getSens() { return sens; }
	public void setSens(Integer sens) { this.sens = sens; }
	
	public Integer getCote() { return cote; }
	public void setCote(Integer cote) { this.cote = cote; }
}
