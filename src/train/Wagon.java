package train;

import java.util.ArrayList;

import comparaison.Preference;
import elements.Evaluable;
import elements.Indexable;

public class Wagon extends Indexable implements Evaluable<Preference>{
	public static final int PREMIERE = 1;
	public static final int SECONDE = 2;
	public static final int BAR = 3;
	
	protected Integer type;
	
	protected ArrayList<Banc> bancs;
	protected ArrayList<Banc> bancsGauches;
	protected ArrayList<Banc> bancsDroits;
	
	public Wagon(){
		type = null;
		bancs = null;
		bancsGauches = null;
		bancsDroits = null;
	}
	
	public Wagon(int i){
		setId(i);		
		setBancs(new ArrayList<Banc>());
		bancsGauches = new ArrayList<Banc>();
		bancsDroits = new ArrayList<Banc>();
	}
	
	public String toString(){
		String res ="";
		String rGauche = "";
		String rDroit = "";
		for(Banc b : bancsGauches){
			rGauche += b.toString() + "\n";
		}
		for(Banc b : bancsDroits){
			rDroit += b.toString() + "\n";
		}
		res += mix(rGauche.split("\n"),rDroit.split("\n"));
		
		return res;
	}
	
	public double eval(Preference pref){
		return 1;
	}
	
	public String mix(String[] str1, String[] str2){
		String res = "";
		
		int i=0;
		int j=0;
		
		while(i<str1.length && j<str2.length){
			res = res.concat(str1[i]);
			res = res.concat(" ");
			res = res.concat(str2[j]);
			res = res.concat("\n");
			i++;
			j++;
		}
		while(i<str1.length){
			res = res.concat(str1[i]);
			res = res.concat(" ");
			res = res.concat("  ");
			res = res.concat("\n");
			i++;
		}
		while(j<str2.length){
			res = res.concat("  ");
			res = res.concat(" ");
			res = res.concat(str2[j]);
			res = res.concat("\n");
			j++;
		}
		
		return res;
	}

	public ArrayList<Banc> getBancs() { return bancs; }
	public void setBancs(ArrayList<Banc> bancs) { this.bancs = bancs; }
}