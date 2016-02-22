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
	
	public Billet(OffreSimple offreSegment, Client client, Siege siege){
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
		return "Billet " + getId() + ", train " + train.getId() + ", client " + client.getId();
	}
	
	public String strPlace(){
		String res = "Wagon n°" + getWagon().getId() + " Banc n°" + getBanc().getId() + " Siege n°" + siege.getId();
		// Classe
		if(getWagon().getType() == Wagon.PREMIERE)
			res += "\nPremière classe";
		else if(getWagon().getType() == Wagon.PREMIERE)
			res += "\nSeconde classe";
		// Sens
		if(siege.getSens() == Siege.AVANT)
			res += "\nSens : marche avant";
		else if(siege.getSens() == Siege.ARRIERE)
			res += "\nSens : marche arrière";
		// Coté
		if(siege.getCote() == Siege.FENETRE)
			res += "\nCoté fenêtre";
		else if(siege.getSens() == Siege.COULOIR)
			res += "\nCoté couloir";
		else
			res += "\nSiege seul";
		return res;
	}
	
	// Recherche	
	public static void chercherBillets(Offre offres, Preference pref, List<Billet>  list){
		for(OffreSimple o : offres.getOffres()){
			ArrayList<Billet> billets = new ArrayList<Billet>();
			
			// Recherche des sieges libres			
			Resultat<Siege> res = new Resultat<Siege>(pref);
			
			Rame rame = o.getTrain().getRame();
			if(rame != null){
				for(Wagon w : rame.getWagons())
					for(Banc b : w.getBancs())
						for(Siege s : b.getSieges())
							if(estLibre(s,o,list))
								res.add(s);
				
				// Premiers sieges
				for(Siege s : res.getMeilleurs(pref.getNbPlace()))
					billets.add(new Billet(o,null,s));
				
				o.setBillets(billets);
			}
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
