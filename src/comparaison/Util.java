package comparaison;

import java.util.ArrayList;

import donnee.Donnees;
import elements.Billet;
import elements.GareHoraire;
import elements.Offre;
import elements.OffreSegment;

import train.Banc;
import train.Rame;
import train.Siege;

import train.Wagon;

public abstract class Util {
	public static double evalDepart(GareHoraire g, Preference p){
		if(p.getLieuxDepart() != null)
			return g.eval(p.getLieuxDepart(), p.getHDepart());
		else
			return 1;
	}
	
	public static double evalArrivee(GareHoraire g, Preference p){
		if(p.getLieuxArrivee() != null)
			return g.eval(p.getLieuxArrivee(), p.getHArrivee());
		else
			return 1;
	}
	
	public static void chercherBillets(Offre offres, Preference pref){
		for(OffreSegment o : offres.getOffres()){
			ArrayList<Billet> billets = new ArrayList<Billet>();
			
			// Recherche des sieges libres
			ArrayList<Siege> sieges = new ArrayList<Siege>();
			Rame rame = o.getTrain().getRame();
			if(rame != null){
				for(Wagon w : rame.getWagons())
					for(Banc b : w.getBancs())
						for(Siege s : b.getSieges())
							if(estLibre(s,o))
								sieges.add(s);
				
				// Premiers sieges
				for(int i=0;i<sieges.size() && i<pref.getNbPlace();i++)
					billets.add(new Billet(o,null,sieges.get(i)));
				
				o.setBillets(billets);
			}
		}
	}
	
	public static boolean estLibre(Siege s, OffreSegment offre){
		for(Billet b : Donnees.getBillets()){
			// si le train du billet est le bon
			if(b.getTrain() == offre.getTrain())
				// si le siege du billet est le bon
				if(b.getSiege() == s)
					// si le billet est pas disjoint a l'offre
					if(! b.getOffreSegment().getSegment().estDisjoint(offre.getSegment())){
						return false;
					}
		}
		return true;
	}
}
