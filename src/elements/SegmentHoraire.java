package elements;

import train.Train;

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
	
	public SegmentHoraire(Train train, GareHoraire depart, GareHoraire arrivee){
		this.depart = depart;
		this.arrivee = arrivee;
	}
	
	public String toString(){
		return depart + " --> " + arrivee;
	}
	
	public boolean estDisjoint(SegmentHoraire s){
		if(s.depart.compareTo(arrivee) > 0 || s.arrivee.compareTo(depart) < 0)
			return true;
		return false;
	}
}
