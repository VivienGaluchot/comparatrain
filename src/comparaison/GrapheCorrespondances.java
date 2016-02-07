package comparaison;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;

import elements.GareHoraire;
import elements.SegmentHoraire;
import train.Train;

public class GrapheCorrespondances {
	DefaultDirectedWeightedGraph<GareHoraire, OffreSegment> graph;
	FloydWarshallShortestPaths<GareHoraire, OffreSegment> FloydWarshallPath;
	
	// Listes permettant de calculer les connections
	
	ArrayList<GareHoraire> departs;
	ArrayList<GareHoraire> arrivees;
	
	public GrapheCorrespondances(List<Train> trains){
		graph = new DefaultDirectedWeightedGraph<GareHoraire,OffreSegment>(OffreSegment.class);
		departs = new ArrayList<GareHoraire>();
		arrivees = new ArrayList<GareHoraire>();
		
		for(Train t : trains){
			addTrain(t);
		}
		
		FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, OffreSegment>(graph);
		connect();
		FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, OffreSegment>(graph);
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
			
			OffreSegment edge = new OffreSegment(t,t.getPlace(),segment);
			graph.addEdge(segment.depart, segment.arrivee, edge);
			graph.setEdgeWeight(edge, edge.getWeight());
			
			if(prevSegment != null){
				graph.addEdge(prevSegment.arrivee, segment.depart, new OffreSegment(t,t.getPlace(),prevSegment.arrivee, segment.depart));
			}
		}
	}
	
	public void connect(){		
		int i = 0;		
		for(GareHoraire A : arrivees){
			for(GareHoraire B : departs){
				if(!A.equals(B) && A.isConnectableTo(B) && FloydWarshallPath.getShortestPath(A,B) == null){
					i++;
					graph.addEdge(A, B, new OffreSegment(null,null,A,B));
				}
			}
		}
		System.out.println(i + " connexions réalisées");
	}
	
	// Recherche d'offre
	
	public List<Offre> trouverOffre(Preference pref){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		for(GareHoraire depart : departs){
			if(Evaluateur.evalDepart(depart,pref) > 0){
				for(GareHoraire arrivee : arrivees){
					if(Evaluateur.evalArrivee(arrivee, pref) > 0){
						resultat.add(trouverOffre(new SegmentHoraire(depart,arrivee)));
					}
				}
			}
		}
		
		return resultat;
	}
	
	public Offre trouverOffre(SegmentHoraire segment){
		Offre resultat = null;
		GraphPath<GareHoraire, OffreSegment> path = FloydWarshallPath.getShortestPath(segment.depart,segment.arrivee);
		if(path != null){
			resultat = (fabriquerOffre(path.getEdgeList()));
		}
		
		return resultat;
	}
	
	public Offre fabriquerOffre(List<OffreSegment> segments){
		Offre resultat = new Offre();
		Train train = null;
		GareHoraire depart = null;
		GareHoraire arrivee = null;
		
		for(OffreSegment s : segments){
			if(s.getTrain() != train){
				if(depart != null && arrivee != null){
					resultat.addOffreSimple(train,train.getPlace(),new SegmentHoraire(train,depart,arrivee));
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
		resultat.addOffreSimple(train,train.getPlace(),new SegmentHoraire(train,depart,arrivee));
		
		return resultat;
	}
}
