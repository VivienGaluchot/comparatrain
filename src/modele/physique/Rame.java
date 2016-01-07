package modele.physique;

import java.util.ArrayList;

import comparaison.Preference;
import modele.Evaluable;

public class Rame implements Evaluable{
	int id;
	ArrayList<Wagon> wagons;
	
	public Rame(int i){
		id = i;
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
		double res = 0;
		for(Wagon w : wagons){
			double t = w.eval(pref);
			if(t>res) res = t;
		}
		return res;
	}
	
	public String toString(){
		String res = "";
		for(Wagon w : wagons){
			res += w.toString() + "\n";
		}
		return res;
	}
}
