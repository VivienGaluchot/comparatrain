/**
 * 
 */
package comparatrain;

import java.util.ArrayList;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Trajet {
	
	Depart depart;
	ArrayList<Escale> escales;
	Arrive arrive;
	
	public Trajet(Depart d, Arrive a){
		depart = d;
		escales = new ArrayList<Escale>();
		arrive = a;
	}
	
	public Trajet(Depart d, ArrayList<Escale> e, Arrive a){
		depart = d;
		escales = e;
		arrive = a;
	}
}

class Depart {
	Gare g;
	Horaire h;
	public Depart(Gare gare, Horaire horaire){
		g = gare;
		h = horaire;
	}
}

class Escale {
	Gare g;
	Horaire hA;
	Horaire hD;
	public Escale(Gare gare, Horaire horaireA, Horaire horaireD){
		g = gare;
		hA = horaireA;
		hD = horaireD;
	}
}

class Arrive {
	Gare g;
	Horaire h;
	public Arrive(Gare gare, Horaire horaire){
		g = gare;
		h = horaire;
	}
}