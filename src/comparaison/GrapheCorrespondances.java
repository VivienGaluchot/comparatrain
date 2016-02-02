package comparaison;

import java.util.Iterator;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultDirectedGraph;

import elements.GareHoraire;
import elements.SegmentHoraire;
import train.Train;

public class GrapheCorrespondances {
	Graph<GareHoraire, SegmentHoraire> graph;
	
	public GrapheCorrespondances(){
		graph = new DefaultDirectedGraph<GareHoraire,SegmentHoraire>(SegmentHoraire.class);
	}
	
	public void addTrain(Train t){
		SegmentHoraire segment;
		
		for(int i=0;i<t.nbStop()-1;i++){
			segment = t.getSegmentHoraire(i, i+1);
			
			graph.addVertex(segment.depart);
			graph.addVertex(segment.arrivee);
			
			graph.addEdge(segment.depart, segment.arrivee, segment);
		}
	}
	
	public void connect(){
		Set<GareHoraire> set = graph.vertexSet();
		
		GareHoraire depart;
		GareHoraire arrivee;
		
		int i = 0;
		
		Iterator<GareHoraire> i1 = set.iterator();
		while(i1.hasNext()){
			depart = i1.next();
			Iterator<GareHoraire> i2 = set.iterator();
			while(i2.hasNext()){
				arrivee = i2.next();
				if(depart != arrivee &&	depart.isConnectedTo(arrivee) && graph.getAllEdges(depart, arrivee)==null){
						i++;
						graph.addEdge(depart, arrivee, new SegmentHoraire(depart,arrivee));
				}
			}
		}
		
		System.out.println(i + " connections réalisées");
	}
}
