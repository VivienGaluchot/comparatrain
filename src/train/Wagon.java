package train;

import java.util.ArrayList;

import comparaison.Preference;
import elements.Evaluable;
import elements.Identified;

public class Wagon extends Identified implements Evaluable<Preference>{
	public static final int PREMIERE = 1;
	public static final int SECONDE = 2;
	public static final int BAR = 3;
	
	int type;
	
	ArrayList<Banc> bancs;
	ArrayList<Banc> bancsGauches;
	ArrayList<Banc> bancsDroits;
	
	public Wagon(int i){
		setId(i);
		
		bancs = new ArrayList<Banc>();
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
		return 0;
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
}

class WagonPremiere extends Wagon{
	public WagonPremiere(int i) {
		super(i);
		type = PREMIERE;
		for(int j=0;j<12;j++){
			bancs.add(new BancSeul(bancs.size(),Banc.GAUCHE));
			bancsGauches.add(bancs.get(bancs.size()-1));
			
			bancs.add(new BancDouble(bancs.size(),Banc.DROIT));
			bancsDroits.add(bancs.get(bancs.size()-1));
		}
	}
	public String toString(){
		String res ="-- -- Wagon " + getId() + " (I)\n";
		res += super.toString();
		res += "-- --\n";
		return res;
	}
}

class WagonSeconde extends Wagon{
	public WagonSeconde(int i) {
		super(i);
		type = SECONDE;
		for(int j=0;j<3;j++){
			bancs.add(new BancDouble(bancs.size(),Banc.GAUCHE));
			bancsGauches.add(bancs.get(bancs.size()-1));
			
			bancs.add(new BancDouble(bancs.size(),Banc.DROIT));
			bancsDroits.add(bancs.get(bancs.size()-1));
			
			bancs.add(new BancCarre(bancs.size(),Banc.GAUCHE));
			bancsGauches.add(bancs.get(bancs.size()-1));
			
			bancs.add(new BancCarre(bancs.size(),Banc.DROIT));
			bancsDroits.add(bancs.get(bancs.size()-1));
			
			bancs.add(new BancDouble(bancs.size(),Banc.GAUCHE));
			bancsGauches.add(bancs.get(bancs.size()-1));
			
			bancs.add(new BancDouble(bancs.size(),Banc.DROIT));
			bancsDroits.add(bancs.get(bancs.size()-1));
		}
	}
	public String toString(){
		String res ="-- -- Wagon " + getId() + " (II)\n";
		res += super.toString();
		res += "-- --\n";
		return res;
	}
}

class WagonBar extends Wagon{
	public WagonBar(int i) {
		super(i);
		type = BAR;
	}
	public String toString(){
		String res = "-- --\n";
		for(int i=0;i<5;i++)
			res += "     \n";
		res+="Wagon\n bar \n";
		for(int i=0;i<5;i++)
			res += "     \n";
		res += "-- --\n";
		return res;
	}
}