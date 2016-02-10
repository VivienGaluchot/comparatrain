package elements;

import utilisateur.Client;

public class Billet extends Indexable{
	private OffreSegment offreSegment;
	private Client client;
	
	Billet(OffreSegment offreSegment, Client client){
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
	
	
}
