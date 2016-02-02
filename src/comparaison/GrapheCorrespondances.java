package comparaison;

import java.util.ArrayList;
import java.util.Iterator;

import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedGraph;

import elements.GareHoraire;
import elements.SegmentHoraire;
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
					if(A.isConnectedTo(B)){
						if(FloydWarshallPath.getShortestPath(A,B) == null){
							System.out.println(A + " --- " + B);
							i++;
							graph.addEdge(A, B, new SegmentHoraire(A,B));
						}
					}
				}
			}
		}
		
		System.out.println(i + " connexions réalisées");
	}
}
