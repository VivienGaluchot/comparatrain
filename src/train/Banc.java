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
	
	public Banc(){
		cote = null;
	}
	
	public Banc(int i, Integer cote, Integer type){
		setId(i);
		this.cote = cote;
		if(type == CARRE){
			getSieges().add(new Siege(getSieges().size(),this,Siege.ARRIERE,Siege.FENETRE));
			getSieges().add(new Siege(getSieges().size(),this,Siege.ARRIERE,Siege.COULOIR));
			getSieges().add(new Siege(getSieges().size(),this,Siege.AVANT,Siege.FENETRE));
			getSieges().add(new Siege(getSieges().size(),this,Siege.AVANT,Siege.COULOIR));
		}else if(type == DOUBLE){
			getSieges().add(new Siege(getSieges().size(),this,Siege.AVANT,Siege.FENETRE));
			getSieges().add(new Siege(getSieges().size(),this,Siege.AVANT,Siege.COULOIR));
		}else if(type == SEUL){
			getSieges().add(new Siege(getSieges().size(),this,Siege.AVANT,Siege.FENETRE));
		}
	}
	
	public double eval(Preference pref){
		// A FAIRE
		return 1;
	}

	public String toString(){
		return "Banc " + getId();
	}

	public ArrayList<Siege> getSieges() { return sieges; }
	public void setSieges(ArrayList<Siege> sieges) { this.sieges = sieges; }

	public Integer getCote() { return cote; }
	public void setCote(Integer cote) { this.cote = cote; }
}