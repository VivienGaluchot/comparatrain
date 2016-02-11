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
		
		wagons.add(new WagonPremiere(0));
		wagons.add(new WagonPremiere(1));
		wagons.add(new WagonBar(2));
		wagons.add(new WagonSeconde(3));
		wagons.add(new WagonSeconde(4));
	}
	
	/**
	 * Renvoie la valeur d'évaluation du meilleur wagon
	 */
	public double eval(Preference pref){
		return 1;
	}
	
	public String toString(){
		return "Rame "+getId();

	}
	
	public ArrayList<Wagon> getWagons(){ return wagons; }
	public void setWagons(ArrayList<Wagon> wagons) { this.wagons = wagons; }
}
