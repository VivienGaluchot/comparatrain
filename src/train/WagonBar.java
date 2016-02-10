package train;

public class WagonBar extends Wagon{
	public WagonBar() {
		super();
		type = BAR;
	}
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