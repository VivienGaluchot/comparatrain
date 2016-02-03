package elements;

import train.Train;

public class SegmentHoraire {
	public GareHoraire depart;
	public GareHoraire arrivee;
	
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
}
