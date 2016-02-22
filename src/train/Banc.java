package train;

import java.util.ArrayList;

import elements.Evaluable;
import elements.Indexable;
import elements.Preference;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représente un Banc de wagon, soit un groupement de sieges
 */
public class Banc extends Indexable implements Evaluable<Preference>{	
	public static final Integer GAUCHE = 0;
	public static final Integer DROIT = 1;
	public static final Integer CARRE = 0;
	public static final Integer DOUBLE = 1;
	public static final Integer SEUL = 2;
	
	private Integer cote;
	private Integer type;
	private ArrayList<Siege> sieges = new ArrayList<Siege>();
	private Wagon father;
	
	public Banc(){
		cote = null;
		father = null;
	}
	
	public Banc(int i, Integer cote, Integer type){
		setId(i);
		setType(type);
		this.cote = cote;
		father = null;
		if(type == CARRE){
			getSieges().add(new Siege(getSieges().size(),Siege.ARRIERE,Siege.FENETRE));
			getSieges().add(new Siege(getSieges().size(),Siege.ARRIERE,Siege.COULOIR));
			getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.FENETRE));
			getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.COULOIR));
		}else if(type == DOUBLE){
			getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.FENETRE));
			getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.COULOIR));
		}else if(type == SEUL){
			getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.FENETRE));
		}
		link();
	}
	
	public double eval(Preference pref){
		return 1;
	}

	public String toString(){
		String sType = new String();
		if(type == CARRE)
			sType="carré";
		else if(type == DOUBLE)
			sType="double";
		else if(type == SEUL)
			sType="seul";
		
		String sCote = new String();
		if(cote == GAUCHE)
			sCote="coté gauche";
		else if(cote == DROIT)
			sCote="coté droit";
		return "Banc " + getId() + " \t" + sType + " \t" + sCote;
	}

	public ArrayList<Siege> getSieges() { return sieges; }
	public void setSieges(ArrayList<Siege> sieges){
		this.sieges = sieges;
		link();
	}

	public Integer getCote() { return cote; }
	public void setCote(Integer cote) { this.cote = cote; }
	
	public Wagon getFather() { return father; }
	public void link(Wagon father){	this.father = father; }
	
	public Integer getType(){ return type; }
	public void setType(Integer i){ type = i; }
	
	public void link(){
		for(Siege s : sieges)
			s.link(this);
	}
}