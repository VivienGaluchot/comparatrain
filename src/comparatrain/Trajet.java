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
	Arrivee arrivee;
	
	public Trajet(){
		depart = null;
		escales = new ArrayList<Escale>();
		arrivee = null;
	}
	
	public Trajet(Depart d, Arrivee a){
		depart = d;
		escales = new ArrayList<Escale>();
		arrivee = a;
	}
	
	public Trajet(Depart d, ArrayList<Escale> e, Arrivee a){
		depart = d;
		escales = e;
		arrivee = a;
	}
}

class Depart {
	Gare gare;
	Horaire horaire;
	public Depart(Gare g, Horaire h){
		gare = g;
		horaire = h;
	}
}

class Escale {
	Gare gare;
	Horaire horaireA;
	Horaire horaireD;
	public Escale(Gare g, Horaire hA, Horaire hD){
		gare = g;
		horaireA = hA;
		horaireD = hD;
	}
}

class Arrivee {
	Gare gare;
	Horaire horaire;
	public Arrivee(Gare g, Horaire h){
		gare = g;
		horaire = h;
	}
}