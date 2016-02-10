package elements;

import train.Rame;
import train.Siege;
import train.Wagon;
import utilisateur.Client;

public class Billet extends Indexable{
	private OffreSegment offreSegment;
	private Client client;
	
	private Rame rame;
	private Wagon wagon;
	private Siege siege;
	
	public Billet(OffreSegment offreSegment, Client client){
		this.setOffreSegment(offreSegment);
		this.setClient(client);
	}

	public OffreSegment getOffreSegment() {
		return offreSegment;
	}

	public void setOffreSegment(OffreSegment offreSegment) {
		this.offreSegment = offreSegment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public String toString(){
		return "Wagon n°" + wagon.getId() + " Siege n°" + siege.getId();
	}

	public Rame getRame() {
		return rame;
	}

	public void setRame(Rame rame) {
		this.rame = rame;
	}

	public Wagon getWagon() {
		return wagon;
	}

	public void setWagon(Wagon wagon) {
		this.wagon = wagon;
	}

	public Siege getSiege() {
		return siege;
	}

	public void setSiege(Siege siege) {
		this.siege = siege;
	}
}
