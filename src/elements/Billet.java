package elements;

import utilisateur.Client;

public class Billet extends Indexable{
	OffreSegment offre;
	Client client;
	
	Billet(OffreSegment offre, Client client){
		this.offre = offre;
		this.client = client;
	}
}
