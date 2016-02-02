package comparaison;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedGraph;

import elements.GareHoraire;
import elements.SegmentHoraire;
import train.Train;

public class GrapheCorrespondances {
	DefaultDirectedGraph<GareHoraire, SegmentHoraire> graph;
	FloydWarshallShortestPaths<GareHoraire, SegmentHoraire> FloydWarshallPath;
	
	// Listes permettant de calculer les connections
	ArrayList<GareHoraire> departs;
	ArrayList<GareHoraire> arrivees;
	
	public GrapheCorrespondances(){
		graph = new DefaultDirectedGraph<GareHoraire,SegmentHoraire>(SegmentHoraire.class);
		departs = new ArrayList<GareHoraire>();
		arrivees = new ArrayList<GareHoraire>();
	}
	
	private void computeFloydWarshallPath(){
		FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, SegmentHoraire>(graph);
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
		computeFloydWarshallPath();
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
		computeFloydWarshallPath();
		System.out.println(i + " connexions réalisées");
	}
	
	public ArrayList<Offre> trouverOffre(SegmentHoraire segment){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		
		GraphPath<GareHoraire, SegmentHoraire> path = FloydWarshallPath.getShortestPath(segment.depart, segment.arrivee);
		List<SegmentHoraire> list = path.getEdgeList();
		
		// A FAIRE - DUR
		
		return resultat;
	}
}
