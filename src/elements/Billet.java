package elements;

import train.Banc;
import train.Rame;
import train.Siege;
import train.Train;
import train.Wagon;

import utilisateur.Client;

public class Billet extends Indexable{
	private SegmentHoraire segment;
	private Train train;
	private Client client;
	private Siege siege;
	
	public Billet(){
		segment = null;
		train = null;
		client = null;
		siege = null;
	}
	
	public Billet(OffreSegment offreSegment, Client client, Siege siege){
		train = offreSegment.getTrain();
		segment = offreSegment.getSegment();
		this.client = client;
		this.siege = siege;
	}
	
	public String toString(){
		return "Billet " + getId() + ", train " + train.getId() + ", client " + client.getId();
	}
	
	public String place(){
		return "Wagon n°" + getWagon().getId() + " Banc n°" + getBanc().getId() + " Siege n°" + siege.getId();
	}

	public SegmentHoraire getSegment() { return segment; }
	public void setSegment(SegmentHoraire segment) { this.segment = segment; }
	
	public Client getClient() { return client; }
	public void setClient(Client client) { this.client = client; }
	
	public Train getTrain() { return train; }
	public void setTrain(Train train) { this.train = train; }

	public Siege getSiege() { return siege; }
	public void setSiege(Siege siege) { this.siege = siege; }

	public Rame getRame() { return siege.getFather().getFather().getFather(); }
	public Wagon getWagon() { return siege.getFather().getFather(); }	
	public Banc getBanc() { return siege.getFather(); }
}
