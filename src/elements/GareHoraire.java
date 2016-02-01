package elements;

public class GareHoraire implements Comparable<GareHoraire>, Evaluable<GareHoraire> {
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

	public double eval(GareHoraire o) {
		Double res = 1.;
		// Lieu
		res *= gare.eval(o.gare);
		if(res == 0) return res;
		// Date)
		res *= horaire.eval(o.horaire);
		return res;
	}
	
	public double eval(String gareStr, Horaire h) {
		Double res = 1.;
		// Lieu
		res *= gare.eval(gareStr);
		if(res == 0) return res;
		// Date)
		if(h!=null)
			res *= horaire.eval(h);
		return res;
	}
}