package train;

import java.util.ArrayList;

import comparaison.Preference;
import elements.Evaluable;
import elements.Indexable;

public class Banc extends Indexable implements Evaluable<Preference>{	
	public static final Integer GAUCHE = 0;
	public static final Integer DROIT = 1;
	public static final Integer CARRE = 0;
	public static final Integer DOUBLE = 1;
	public static final Integer SEUL = 2;
	
	
	private Integer cote;
	private ArrayList<Siege> sieges = new ArrayList<Siege>();
	private Wagon father;
	
	public Banc(){
		cote = null;
		father = null;
	}
	
	public Banc(int i, Integer cote, Integer type){
		setId(i);
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
		return "Banc " + getId();
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
	
	public void link(){
		for(Siege s : sieges)
			s.link(this);
	}
}