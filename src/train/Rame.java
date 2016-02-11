package train;

import java.util.ArrayList;

import comparaison.Preference;
import elements.Evaluable;
import elements.Indexable;

public class Rame extends Indexable implements Evaluable<Preference>{
	private ArrayList<Wagon> wagons;
	
	public Rame(){
		wagons = null;
	}
	
	public Rame(int i){
		setId(i);
		wagons = new ArrayList<Wagon>();
		
		wagons.add(new Wagon(0,Wagon.PREMIERE));
		wagons.add(new Wagon(1,Wagon.PREMIERE));
		wagons.add(new Wagon(2,Wagon.BAR));
		wagons.add(new Wagon(3,Wagon.SECONDE));
		wagons.add(new Wagon(4,Wagon.SECONDE));
	}
	
	/**
	 * Renvoie la valeur d'évaluation du meilleur wagon
	 */
	public double eval(Preference pref){
		return 1;
	}
	
	public String toString(){
		return "Rame " + getId();
	}
	
	public ArrayList<Wagon> getWagons(){ return wagons; }
	public void setWagons(ArrayList<Wagon> wagons) { this.wagons = wagons; }
	
	public void link(){
		for(Wagon w : wagons)
			w.link(this);
	}
}