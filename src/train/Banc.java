package train;

import java.util.ArrayList;

import comparaison.Preference;
import elements.Evaluable;
import elements.Indexable;

public class Banc extends Indexable implements Evaluable<Preference>{	
	public static final Integer GAUCHE = 0;
	public static final Integer DROIT = 1;
	
	private Integer cote;
	private ArrayList<Siege> sieges = new ArrayList<Siege>();
	
	public Banc(){
		cote = null;
	}
	
	public Banc(int i, Integer cote){
		setId(i);
		this.cote = cote;
	}
	
	public double eval(Preference pref){
		// A FAIRE
		return 1;
	}

	public String toString(){
		return "--";
	}

	public ArrayList<Siege> getSieges() { return sieges; }
	public void setSieges(ArrayList<Siege> sieges) { this.sieges = sieges; }

	public Integer getCote() { return cote; }
	public void setCote(Integer cote) { this.cote = cote; }
}