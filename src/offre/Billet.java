package offre;

import java.util.ArrayList;
import java.util.List;

import elements.Evaluable;
import elements.Indexable;
import elements.Preference;
import elements.SegmentHoraire;
import train.Banc;
import train.Rame;
import train.Siege;
import train.Train;
import train.Wagon;
import utilisateur.Client;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représente un Billet de train
 */
public class Billet extends Indexable implements Evaluable<Preference>{
	private static int compteur = 0;
	private SegmentHoraire segment;
	private Train train;
	private Client client;
	private Siege siege;
	
	public Billet(){
		setId(null);
		segment = null;
		train = null;
		client = null;
		siege = null;
	}
	
	public Billet(OffreSimple offreSegment, Client client, Siege siege){
		setId(compteur++);
		train = offreSegment.getTrain();
		segment = offreSegment.getSegment();
		this.client = client;
		this.siege = siege;
	}
	
	public double eval(Preference pref){
		Double res = 1.;
		res *= getRame().eval(pref);
		res *= getWagon().eval(pref);
		res *= getSiege().eval(pref);
		return res;
	}
	
	public String toString(){
		String res = "Billet " + getId();
		if(train != null)
			res += ", train " + train.getId();
		if(client != null)
			res += ", client " + client.getId();
		return res;
	}
	
	public String strPlace(){
		String res = "Wagon n°" + getWagon().getId();
		if(getWagon().getType() == Wagon.PREMIERE)
			res += "\tPremière classe";
		else if(getWagon().getType() == Wagon.SECONDE)
			res += "\tSeconde classe";
		
		res += "\nBanc n°" + getBanc().getId();
		if(siege.getSens() == Siege.AVANT)
			res += "\tSens de la marche";
		else if(siege.getSens() == Siege.ARRIERE)
			res += "\tMarche arrière";
		
		res += "\nSiège n°" + siege.getId();
		if(siege.getCote() == Siege.FENETRE)
			res +=  "\tCoté fenêtre";
		else if(siege.getSens() == Siege.COULOIR)
			res += "\tCoté couloir";
		else
			res += "\tSiege seul";
		return res;
	}
	
	// Recherche	
	public static void chercherBillets(Offre offres, Preference pref, List<Billet>  list){
		for(OffreSimple o : offres.getOffres()){
			Resultat<Billet> res = new Resultat<Billet>(pref);			
			Rame rame = o.getTrain().getRame();
			if(rame != null){
				for(Wagon w : rame.getWagons())
					for(Banc b : w.getBancs())
						for(Siege s : b.getSieges())
							if(estLibre(s,o,list))
								res.add(new Billet(o,null,s));
			}
			
			ArrayList<Billet> billets = new ArrayList<Billet>();
			billets.addAll(res.getMeilleurs(pref.getNbPlace()));
			o.setBillets(billets);
		}
	}
	
	public static boolean estLibre(Siege s, OffreSimple offre, List<Billet>  list){
		for(Billet b : list){
			// si le train du billet est le bon
			if(b.getTrain() == offre.getTrain())
				// si le siege du billet est le bon
				if(b.getSiege() == s)
					// si le billet est pas disjoint a l'offre
					if(! b.getSegment().estDisjoint(offre.getSegment())){
						return false;
					}
		}
		return true;
	}
	
	// Getters, setters
	public SegmentHoraire getSegment() { return segment; }
	public void setSegment(SegmentHoraire segment) { this.segment = segment; }
	
	public Client getClient() { return client; }
	public void setClient(Client client) { this.client = client; }
	
	public Train getTrain() { return train; }
	public void setTrain(Train train) { this.train = train; }

	public Siege getSiege() { return siege; }
	public void setSiege(Siege siege) { this.siege = siege; }

	public Rame getRame() { return getWagon().getFather(); }
	public Wagon getWagon() { return getBanc().getFather(); }	
	public Banc getBanc() { return siege.getFather(); }
}
