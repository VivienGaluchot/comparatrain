package elements;

import train.Banc;
import train.Rame;
import train.Siege;
import train.Train;
import train.Wagon;
import utilisateur.Client;

public class Billet extends Indexable{
	private OffreSegment offreSegment;
	private Client client;
	private Siege siege;
	
	public Billet(){
		siege = null;
	}
	
	public Billet(OffreSegment offreSegment, Client client, Siege siege){
		this.offreSegment = offreSegment;
		this.client = client;
		this.siege = siege;
	}
	
	public String toString(){
		return "Wagon n°" + getWagon().getId() + " Banc n°" + getBanc().getId() + " Siege n°" + siege.getId();
	}

	public OffreSegment getOffreSegment() { return offreSegment; }
	public void setOffreSegment(OffreSegment offreSegment) { this.offreSegment = offreSegment; }

	public Client getClient() { return client; }
	public void setClient(Client client) { this.client = client; }

	public Train getTrain() { return offreSegment.getTrain(); }
	public Rame getRame() { return siege.getFather().getFather().getFather(); }
	public Wagon getWagon() { return siege.getFather().getFather(); }	
	public Banc getBanc() { return siege.getFather(); }

	public Siege getSiege() { return siege; }
	public void setSiege(Siege siege) { this.siege = siege; }
}
