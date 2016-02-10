package train;

public class BancDouble extends Banc{
	public BancDouble() { super(); }
	
	public BancDouble(int i, Integer cote){
		super(i,cote);
		getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.FENETRE));
		getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.COULOIR));
	}
	
	public String toString(){
		String res;
		res = getSieges().get(0).toString() + getSieges().get(1);
		return res;
	}
}
