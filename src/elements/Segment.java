package elements;

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
