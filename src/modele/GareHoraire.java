package modele;

public class GareHoraire implements Comparable<GareHoraire> {
	public Gare gare;
	public Horaire horaire;
	
	public GareHoraire(){
		gare = null;
		horaire = null;
	}
	
	public GareHoraire(Gare g, Horaire h){
		gare = g;
		horaire = h;
	}
	
	public String toString(){
		return gare + " " + horaire;
	}
	
	public int compareTo(GareHoraire o){
		return horaire.compareTo(o.horaire);
	}
}