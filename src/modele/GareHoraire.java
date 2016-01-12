package modele;

public class GareHoraire {
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
}