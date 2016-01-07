/**
 * 
 */
package modele;

import java.util.ArrayList;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
 */
public class Trajet {
	
	private Depart depart;
	private ArrayList<Escale> escales;
	private Arrivee arrivee;
	
	public Trajet(){
		setDepart(null);
		setEscales(new ArrayList<Escale>());
		setArrivee(null);
	}
	
	public Trajet(Depart d, Arrivee a){
		setDepart(d);
		setEscales(new ArrayList<Escale>());
		setArrivee(a);
	}
	
	public Trajet(Gare gD, Horaire hD, Gare gA, Horaire hA){
		depart = new Depart(gD,hD);
		setEscales(new ArrayList<Escale>());
		arrivee = new Arrivee(gA,hA);
	}
	
	public Trajet(Depart d, ArrayList<Escale> e, Arrivee a){
		setDepart(d);
		setEscales(e);
		setArrivee(a);
	}

	public Depart getDepart() {
		return depart;
	}

	public void setDepart(Gare g, Horaire h) {
		depart = new Depart(g,h);
	}
	
	public void setDepart(Depart depart) {
		this.depart = depart;
	}

	public Arrivee getArrivee() {
		return arrivee;
	}

	public void setArrivee(Arrivee arrivee) {
		this.arrivee = arrivee;
	}
	
	public void setArrivee(Gare g, Horaire h) {
		arrivee = new Arrivee(g,h);
	}

	public ArrayList<Escale> getEscales() {
		return escales;
	}
	
	public void addEscale(Gare g, Horaire hA, Horaire hD) {
		escales.add(new Escale(g,hA,hD));
	}

	public void setEscales(ArrayList<Escale> escales) {
		this.escales = escales;
	}
	
	public class Depart {
		Gare gare;
		Horaire horaire;
		public Depart(Gare g, Horaire h){
			gare = g;
			horaire = h;
		}
		
		public String toData(){
			return gare + " : " + horaire;
		}
	}

	public class Escale {
		Gare gare;
		Horaire horaireA;
		Horaire horaireD;
		
		public Escale(Gare g, Horaire hA, Horaire hD){
			gare = g;
			horaireA = hA;
			horaireD = hD;
		}
		
		public String toData(){
			return gare + " : " + horaireA + " " + horaireD;
		}
	}

	public class Arrivee {
		Gare gare;
		Horaire horaire;
		public Arrivee(Gare g, Horaire h){
			gare = g;
			horaire = h;
		}
		
		public String toData(){
			return gare + " : " + horaire;
		}
	}
}