package train;

import java.util.ArrayList;

import elements.Evaluable;
import elements.Indexable;
import elements.Preference;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Représente un Wagon, soit une collection de bancs
 * Le type du Wagon représente sa classe, ou alors s'il est un wagon Bar
 */
public class Wagon extends Indexable implements Evaluable<Preference>{
	public static final int PREMIERE = 1;
	public static final int SECONDE = 2;
	public static final int BAR = 3;
	
	private ArrayList<Banc> bancs;
	private Rame father;
	private Integer type;
	
	public Wagon(){
		bancs = null;
		father = null;
		type = null;
	}
	
	public Wagon(int i, Integer type){
		setId(i);		
		bancs = new ArrayList<Banc>();
		father = null;
		setType(type);
	}
	
	public String toString(){	
		String sType = new String();
		if(type == PREMIERE){
			sType="Première classe";
			
		}else if(type == SECONDE){
			sType="Seconde classe";
			
		}else if(type == BAR){
			sType="Bar";
		}
		return "Wagon " + getId() + " \t" + sType;
	}
	
	public double eval(Preference pref){
		if(pref.getClasse() != null && type != pref.getClasse()) return 0.9;
		return 1;
	}

	public ArrayList<Banc> getBancs() { return bancs; }
	public void setBancs(ArrayList<Banc> bancs) {
		this.bancs = bancs;
		link();
	}
	
	public Rame getFather() { return father; }
	public void link(Rame father){ this.father = father;	}
	
	public void link(){
		for(Banc b : bancs)
			b.link(this);
	}	
	
	public Integer getType(){ return type; }
	public void setType(Integer i){
		type = i;
		if(type == PREMIERE){
			for(int j=0;j<3;j++){
				bancs.add(new Banc(bancs.size(),Banc.GAUCHE,Banc.SEUL));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.DOUBLE));
			}
		}else if(type == SECONDE){
			for(int j=0;j<1;j++){
				bancs.add(new Banc(bancs.size(),Banc.GAUCHE,Banc.DOUBLE));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.DOUBLE));
				bancs.add(new Banc(bancs.size(),Banc.GAUCHE,Banc.CARRE));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.CARRE));
				bancs.add(new Banc(bancs.size(),Banc.GAUCHE,Banc.DOUBLE));
				bancs.add(new Banc(bancs.size(),Banc.DROIT,Banc.DOUBLE));
			}
		}else if(type == BAR){
			// pas de bancs
		}
		link();
	}
}