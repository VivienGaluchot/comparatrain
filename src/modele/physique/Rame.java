package modele.physique;

import java.util.ArrayList;

import comparaison.Preference;
import modele.Evaluable;

public class Rame implements Evaluable{
	int id;
	ArrayList<Wagon> wagons;
	
	public void addWagon(int classe){
		wagons.add(new Wagon(classe));
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
}
