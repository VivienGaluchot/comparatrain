package train;

public class BancCarre extends Banc{
	public BancCarre() { super(); }
	
	public BancCarre(int i, Integer cote){
		super(i,cote);
		getSieges().add(new Siege(getSieges().size(),Siege.ARRIERE,Siege.FENETRE));
		getSieges().add(new Siege(getSieges().size(),Siege.ARRIERE,Siege.COULOIR));
		getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.FENETRE));
		getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.COULOIR));
	}
	
	public String toString(){
		String res;
		res = getSieges().get(0).toString() + getSieges().get(1) + "\n";
		res += getSieges().get(2).toString() + getSieges().get(3);
		return res;
	}
}