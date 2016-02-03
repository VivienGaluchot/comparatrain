package comparaison;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedGraph;

import donnée.Donnees;
import elements.GareHoraire;
import elements.SegmentHoraire;
import gui.MaFenetre;
import train.Train;

public class GrapheCorrespondances {
	DefaultDirectedGraph<GareHoraire, SegmentHoraire> graph;
	
	// Listes permettant de calculer les connections
	ArrayList<GareHoraire> departs;
	ArrayList<GareHoraire> arrivees;
	
	public GrapheCorrespondances(){
		graph = new DefaultDirectedGraph<GareHoraire,SegmentHoraire>(SegmentHoraire.class);
		departs = new ArrayList<GareHoraire>();
		arrivees = new ArrayList<GareHoraire>();
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
			
			graph.addEdge(segment.depart, segment.arrivee, segment);
			
			if(prevSegment != null){
				graph.addEdge(prevSegment.arrivee, segment.depart,new SegmentHoraire(t,prevSegment.arrivee, segment.depart));
			}
		}
	}
	
	public void connect(){
		FloydWarshallShortestPaths<GareHoraire, SegmentHoraire> FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, SegmentHoraire>(graph);
		
		int i = 0;		
		for(GareHoraire A : arrivees){
			for(GareHoraire B : departs){
				if(!A.equals(B)){
					if(A.isConnectableTo(B)){
						if(FloydWarshallPath.getShortestPath(A,B) == null){
							i++;
							graph.addEdge(A, B, new SegmentHoraire(A,B));
						}
					}
				}
			}
		}
		System.out.println(i + " connexions réalisées");
	}
	
	public List<Offre> trouverOffre(SegmentHoraire segment){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		DijkstraShortestPath<GareHoraire, SegmentHoraire> dijkstra = null;
		List<SegmentHoraire> list = null;
		
		DefaultDirectedGraph<GareHoraire, SegmentHoraire> tempGraph = (DefaultDirectedGraph<GareHoraire, SegmentHoraire>) graph.clone();
		
		do{
			dijkstra = new DijkstraShortestPath<GareHoraire, SegmentHoraire>(tempGraph,segment.depart,segment.arrivee);
			list = dijkstra.getPathEdgeList();
			if(list != null){
				resultat.add(fabriquerOffre(list));
				tempGraph.removeAllEdges(list);
			}
		}while(list != null);
		
		return resultat;
	}
	
	public Offre fabriquerOffre(List<SegmentHoraire> segments){
		OffreMultiple o = new OffreMultiple();
		Train train = null;
		GareHoraire depart = null;
		GareHoraire arrivee = null;
		
		for(SegmentHoraire s : segments){
			if(s.train != train){
				if(depart != null && arrivee != null){
					o.addOffreSimple(train,train.getPlace(),new SegmentHoraire(train,depart,arrivee));
					depart = null;
					train = null;
				}
				else{
					train = s.train;
					depart = s.depart;
				}
			}
			arrivee = s.arrivee;
		}
		o.addOffreSimple(train,train.getPlace(),new SegmentHoraire(train,depart,arrivee));
		
		return o;
	}
}
