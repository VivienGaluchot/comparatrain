package comparaison;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.FloydWarshallShortestPaths;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import elements.GareHoraire;
import elements.SegmentHoraire;
import train.Place;
import train.Train;

public class GrapheCorrespondances {
	DefaultDirectedWeightedGraph<GareHoraire, myEdge> graph;
	FloydWarshallShortestPaths<GareHoraire, myEdge> FloydWarshallPath;
	
	// Listes permettant de calculer les connections
	
	ArrayList<GareHoraire> departs;
	ArrayList<GareHoraire> arrivees;
	
	public GrapheCorrespondances(List<Train> trains){
		graph = new DefaultDirectedWeightedGraph<GareHoraire,myEdge>(myEdge.class);
		departs = new ArrayList<GareHoraire>();
		arrivees = new ArrayList<GareHoraire>();
		
		for(Train t : trains){
			addTrain(t);
		}
		
		FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, myEdge>(graph);
		connect();
		FloydWarshallPath = new FloydWarshallShortestPaths<GareHoraire, myEdge>(graph);
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
			
			myEdge edge = new myEdge(t,t.getPlace(),segment);
			graph.addEdge(segment.depart, segment.arrivee, edge);
			graph.setEdgeWeight(edge, edge.getWeight());
			
			if(prevSegment != null){
				graph.addEdge(prevSegment.arrivee, segment.depart, new myEdge(t,t.getPlace(),prevSegment.arrivee, segment.depart));
			}
		}
	}
	
	public void connect(){		
		int i = 0;		
		for(GareHoraire A : arrivees){
			for(GareHoraire B : departs){
				if(!A.equals(B)){
					if(A.isConnectableTo(B)){
						if(FloydWarshallPath.getShortestPath(A,B) == null){
							i++;
							graph.addEdge(A, B, new myEdge(null,null,A,B));
						}
					}
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
						resultat.addAll(trouverOffre(new SegmentHoraire(depart,arrivee)));
					}
				}
			}
		}
		
		return resultat;
	}
	
	public List<Offre> trouverOffre(SegmentHoraire segment){
		ArrayList<Offre> resultat = new ArrayList<Offre>();
		GraphPath<GareHoraire, myEdge> path = FloydWarshallPath.getShortestPath(segment.depart,segment.arrivee);
		if(path != null){
			resultat.add(fabriquerOffre(path.getEdgeList()));
		}
		
		return resultat;
	}
	
	public Offre fabriquerOffre(List<myEdge> segments){
		Offre o = new Offre();
		Train train = null;
		GareHoraire depart = null;
		GareHoraire arrivee = null;
		
		for(myEdge s : segments){
			if(s.getTrain() != train){
				if(depart != null && arrivee != null){
					o.addOffreSimple(train,train.getPlace(),new SegmentHoraire(train,depart,arrivee));
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
		o.addOffreSimple(train,train.getPlace(),new SegmentHoraire(train,depart,arrivee));
		
		return o;
	}
	
	@SuppressWarnings("serial")
	private class myEdge extends DefaultWeightedEdge{
		OffreSegment offre;
		
		public myEdge(Train train, Place place, SegmentHoraire segment) {
			offre = new OffreSegment(train, place, segment);
		}

		public myEdge(Train t, Place place, GareHoraire arrivee, GareHoraire depart) {
			offre = new OffreSegment(t, place, arrivee, depart);
		}
		
		public GareHoraire getDepart(){
			return offre.getDepart();
		}
		
		public GareHoraire getArrivee(){
			return offre.getArrivee();
		}
		
		public Train getTrain(){
			return offre.train;
		}

		public double getWeight(){
			return (double) getDepart().horaire.until(getArrivee().horaire);
		}
	}
}
