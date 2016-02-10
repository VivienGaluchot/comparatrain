package train;

public class WagonSeconde extends Wagon{
	public WagonSeconde() {
		super();
		type = SECONDE;
	}
	public WagonSeconde(int i) {
		super(i);
		type = SECONDE;
		for(int j=0;j<1;j++){
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