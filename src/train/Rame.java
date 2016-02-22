package train;

import java.util.ArrayList;

import elements.Evaluable;
import elements.Indexable;
import elements.Preference;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représente une Rame, soit une suite de Wagons
 */
public class Rame extends Indexable implements Evaluable<Preference>{
	private ArrayList<Wagon> wagons;
	
	public Rame(){
		wagons = new ArrayList<Wagon>();
	}
	
	public Rame(int i){
		setId(i);
		wagons = new ArrayList<Wagon>();
		
		wagons.add(new Wagon(0,Wagon.PREMIERE));
		wagons.add(new Wagon(1,Wagon.PREMIERE));
		wagons.add(new Wagon(2,Wagon.BAR));
		wagons.add(new Wagon(3,Wagon.SECONDE));
		wagons.add(new Wagon(4,Wagon.SECONDE));
		
		link();
	}
	
	public double eval(Preference pref){
		if(pref.getBar() != null && pref.getBar())
			for(Wagon w : wagons)
				if(w.getType() == Wagon.BAR)
					return 1.;
		return 0.9;
	}
	
	public String toString(){
		return "Rame de " + wagons.size() + " wagons";
	}
	
	public ArrayList<Wagon> getWagons(){ return wagons; }
	public void setWagons(ArrayList<Wagon> wagons) {
		this.wagons = wagons;
		link();
	}
	
	public void link(){
		for(Wagon w : wagons)
			w.link(this);
	}
}