package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Un SegmentHoraire est composé d'une GareHoraire de départ et d'une d'arrivée
 */
public class SegmentHoraire {
	public GareHoraire depart;
	public GareHoraire arrivee;
	
	public SegmentHoraire(){
		depart = null;
		arrivee = null;
	}
	
	public SegmentHoraire(GareHoraire depart, GareHoraire arrivee){
		this.depart = depart;
		this.arrivee = arrivee;
	}
	
	public String toString(){
		return depart + " --> " + arrivee;
	}
	
	public boolean estCoherent(){
		return depart.compareTo(arrivee) < 0;
	}
	
	/**
	 * @param s
	 * @return true seulement si le segment s n'est pas temporellement supperposé
	 * et si les deux segments sont cohérents
	 */
	public boolean estDisjoint(SegmentHoraire s){
		if(estCoherent() && s.estCoherent() && s.depart.compareTo(arrivee) > 0 || s.arrivee.compareTo(depart) < 0)
			return true;
		return false;
	}
}
