package train;

public class WagonPremiere extends Wagon{
	public WagonPremiere() {
		super();
		type = PREMIERE;
	}
	public WagonPremiere(int i) {
		super(i);
		type = PREMIERE;
		for(int j=0;j<3;j++){
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