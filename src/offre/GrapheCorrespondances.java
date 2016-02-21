package offre;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import elements.GareHoraire;
import elements.Preference;
import elements.SegmentHoraire;
import train.Train;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Graphe utilisant la bibliotheque JGraphT
 * Il permet de représenter les correspondances entre les trains
 * et d'effectuer une recherche des offres pour aller d'une gare a une autre
 */
public class GrapheCorrespondances {
	DefaultDirectedWeightedGraph<GareHoraire, OffreSimple> graph;
	FloydWarshallShortestPaths<GareHoraire, OffreSimple> FloydWarshallPath;
	
	// Listes permettant de calculer les connections
	
	ArrayList<GareHoraire> departs;
	ArrayList<GareHoraire> arrivees;
	
	public GrapheCorrespondances(List<Train> trains){
		graph = new DefaultDirectedWeightedGraph<GareHoraire,OffreSimple>(OffreSimple.class);
		departs = new ArrayList<GareHoraire>();
		arrivees = new ArrayList<GareHoraire>();
		
		for(Train t : trains){
			addTrain(t);
		}
		
		FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, OffreSimple>(graph);
		connect();
		FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, OffreSimple>(graph);
	}
	
	public void addTrain(Train t){
		SegmentHoraire segment = null;
		SegmentHoraire prevSegment = null;
		
		for(int i=0;i<t.nbStop()-1;i++){
			prevSegment = segment;
			segment = t.getSegmentHoraire(i, i+1);
			
			graph.addVertex(segment.depart);
			graph.addVertex(segment.arrivee);
			
			departs.add(segment.depart);
			arrivees.add(segment.arrivee);
			
			OffreSimple edge = new OffreSimple(t,segment);
			graph.addEdge(segment.depart, segment.arrivee, edge);
			graph.setEdgeWeight(edge, edge.getWeight());
			
			if(prevSegment != null){
				graph.addEdge(prevSegment.arrivee, segment.depart, new OffreSimple(t,prevSegment.arrivee, segment.depart));
			}
		}
	}
	
	public void connect(){		
		int i = 0;		
		for(GareHoraire A : arrivees){
			for(GareHoraire B : departs){
				if(!A.equals(B) && A.isConnectableTo(B) && FloydWarshallPath.getShortestPath(A,B) == null){
					i++;
					graph.addEdge(A, B, new OffreSimple(null,A,B));
				}
			}
		}
		System.out.println(i + " connexions réalisées");
	}
	
	// Recherche d'offre
	
	public List<Offre> trouverOffre(Preference pref){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		for(GareHoraire depart : departs){
			if(evalDepart(depart,pref) > 0){
				for(GareHoraire arrivee : arrivees){
					if(evalArrivee(arrivee, pref) > 0){
						Offre o = trouverOffre(new SegmentHoraire(depart,arrivee));
						if(o != null)
							resultat.add(o);
					}
				}
			}
		}
		
		return resultat;
	}
	
	public Offre trouverOffre(SegmentHoraire segment){
		Offre resultat = null;
		GraphPath<GareHoraire, OffreSimple> path = FloydWarshallPath.getShortestPath(segment.depart,segment.arrivee);
		if(path != null){
			resultat = (fabriquerOffre(path.getEdgeList()));
		}
		return resultat;
	}
	
	public Offre fabriquerOffre(List<OffreSimple> segments){
		Offre resultat = new Offre();
		Train train = null;
		GareHoraire depart = null;
		GareHoraire arrivee = null;
		
		for(OffreSimple s : segments){
			if(s.getTrain() != train){
				if(depart != null && arrivee != null){
					resultat.addOffreSimple(train,new SegmentHoraire(depart,arrivee));
					depart = null;
					train = null;
				}
				else{
					train = s.getTrain();
					depart = s.getDepart();
				}
			}
			arrivee = s.getArrivee();
		}
		resultat.addOffreSimple(train,new SegmentHoraire(depart,arrivee));
		
		return resultat;
	}
	
	// Utilitaire
	private double evalDepart(GareHoraire g, Preference p){
		if(p.getLieuxDepart() != null)
			return g.eval(p.getLieuxDepart(), p.getHDepart());
		else
			return 1;
	}
	
	private double evalArrivee(GareHoraire g, Preference p){
		if(p.getLieuxArrivee() != null)
			return g.eval(p.getLieuxArrivee(), p.getHArrivee());
		else
			return 1;
	}
}
