package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Un Segment représente physiquement un segment de rail, allant d'une garde de départ
 * à une gare d'arrivée
 */
public class Segment {
	public Gare depart;
	public Gare arrivee;
	
	public Segment(Gare depart, Gare arrivee){
		this.depart = depart;
		this.arrivee = arrivee;
	}
	
	public String toString(){
		return depart + " --> " + arrivee;
	}
}
