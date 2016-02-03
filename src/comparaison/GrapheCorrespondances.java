package comparaison;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedGraph;

import elements.GareHoraire;
import elements.SegmentHoraire;
import train.Train;

public class GrapheCorrespondances {
	DefaultDirectedGraph<GareHoraire, OffreSegment> graph;
	
	// Listes permettant de calculer les connections
	
	ArrayList<GareHoraire> departs;
	ArrayList<GareHoraire> arrivees;
	
	public GrapheCorrespondances(List<Train> trains){
		graph = new DefaultDirectedGraph<GareHoraire,OffreSegment>(OffreSegment.class);
		departs = new ArrayList<GareHoraire>();
		arrivees = new ArrayList<GareHoraire>();
		
		for(Train t : trains){
			addTrain(t);
		}
		
		connect();
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
			
			graph.addEdge(segment.depart, segment.arrivee, new OffreSegment(t,t.getPlace(),segment));
			
			if(prevSegment != null){
				graph.addEdge(prevSegment.arrivee, segment.depart, new OffreSegment(t,t.getPlace(),prevSegment.arrivee, segment.depart));
			}
		}
	}
	
	public void connect(){
		FloydWarshallShortestPaths<GareHoraire, OffreSegment> FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, OffreSegment>(graph);
		
		int i = 0;		
		for(GareHoraire A : arrivees){
			for(GareHoraire B : departs){
				if(!A.equals(B)){
					if(A.isConnectableTo(B)){
						if(FloydWarshallPath.getShortestPath(A,B) == null){
							i++;
							graph.addEdge(A, B, new OffreSegment(null,null,A,B));
						}
					}
				}
			}
		}
		System.out.println(i + " connexions réalisées");
	}
	
	public List<Offre> trouverOffre(Preference pref){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		for(GareHoraire depart : departs){
			if(Evaluateur.evalDepart(depart,pref) > 0){
				for(GareHoraire arrivee : arrivees){
					if(Evaluateur.evalArrivee(arrivee, pref) > 0){
						resultat.addAll(trouverOffre(new SegmentHoraire(depart,arrivee)));
					}
				}
			}
		}
		
		return resultat;
	}
	
	public List<Offre> trouverOffre(SegmentHoraire segment){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		DijkstraShortestPath<GareHoraire, OffreSegment> dijkstra = null;
		List<OffreSegment> list = null;
		
		@SuppressWarnings("unchecked")
		DefaultDirectedGraph<GareHoraire, OffreSegment> tempGraph = (DefaultDirectedGraph<GareHoraire, OffreSegment>) graph.clone();
		
		do{
			dijkstra = new DijkstraShortestPath<GareHoraire, OffreSegment>(tempGraph,segment.depart,segment.arrivee);
			list = dijkstra.getPathEdgeList();
			if(list != null){
				resultat.add(fabriquerOffre(list));
				tempGraph.removeAllEdges(list);
			}
		}while(list != null);
		
		return resultat;
	}
	
	public Offre fabriquerOffre(List<OffreSegment> segments){
		Offre o = new Offre();
		Train train = null;
		GareHoraire depart = null;
		GareHoraire arrivee = null;
		
		for(OffreSegment s : segments){
			if(s.train != train){
				if(depart != null && arrivee != null){
					o.addOffreSimple(train,train.getPlace(),new SegmentHoraire(train,depart,arrivee));
					depart = null;
					train = null;
				}
				else{
					train = s.train;
					depart = s.getDepart();
				}
			}
			arrivee = s.getArrivee();
		}
		o.addOffreSimple(train,train.getPlace(),new SegmentHoraire(train,depart,arrivee));
		
		return o;
	}
}
