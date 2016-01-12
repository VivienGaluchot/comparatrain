package modele;

public class Escale {
	public Gare gare;
	public Horaire horaireA;
	public Horaire horaireD;
	
	public Escale(){
		gare = null;
		horaireA = null;
		horaireD = null;
	}
	
	public Escale(Gare g, Horaire hA, Horaire hD){
		gare = g;
		horaireA = hA;
		horaireD = hD;
	}
	
	public String toString(){
		return gare + " " + horaireA + " " + horaireD;
	}
}