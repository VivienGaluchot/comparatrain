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
	
	private Banc father;
	
	public Siege(){
		sens = null;
		father = null;
		setCote(null);
	}
	
	public Siege(Integer i, Integer sens, Integer cote){
		setId(i);
		this.sens = sens;
		father = null;
		this.setCote(cote);
	}
	
	public Siege(Integer i,Integer sens, Integer cote, boolean occupe){
		setId(i);
		this.sens = sens;
		father = null;
		this.setCote(cote);
	}
	
	public double eval(Preference pref){
		Double res = 1.;
		if(pref.getSens() != null && sens != pref.getSens()) res *= 0.9;
		if(pref.getCote() != null && cote != pref.getCote()) res *= 0.9;
		return res;
	}
	
	public String toString(){
		return "Siege " + getId();
	}

	public Integer getSens() { return sens; }
	public void setSens(Integer sens) { this.sens = sens; }
	
	public Integer getCote() { return cote; }
	public void setCote(Integer cote) { this.cote = cote; }
	
	public Banc getFather() { return father; }
	public void link(Banc father){ this.father = father; }
}
