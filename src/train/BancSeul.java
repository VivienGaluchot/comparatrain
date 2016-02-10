package train;

public class BancSeul extends Banc{
	public BancSeul(){ super(); }
	
	public BancSeul(int i, Integer cote){
		super(i,cote);
		getSieges().add(new Siege(getSieges().size(),Siege.AVANT,Siege.FENETRE));
	}
	
	public String toString(){
		String res;
		if(getCote() == GAUCHE)
			res = getSieges().get(0) + " ";
		else
			res = " " + getSieges().get(0);
		return res;
	}
}