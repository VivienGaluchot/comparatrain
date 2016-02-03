package elements;

import train.Train;

public class SegmentHoraire {
	public GareHoraire depart;
	public GareHoraire arrivee;
	public Train train;
	
	public SegmentHoraire(GareHoraire depart, GareHoraire arrivee){
		this.depart = depart;
		this.arrivee = arrivee;
		train = null;
	}
	
	public SegmentHoraire(Train train, GareHoraire depart, GareHoraire arrivee){
		this.depart = depart;
		this.arrivee = arrivee;
		this.train = train;
	}
	
	public String toString(){
		if(train == null)
			return depart + " --C--> " + arrivee;
		else
			return depart + " --Train " + train.getId() + "--> " + arrivee;
	}
}
