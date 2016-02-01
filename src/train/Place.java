package train;

public class Place {
	Rame rame;
	Wagon wagon;
	Siege siege;
	
	public Place(Rame rame, Wagon wagon, Siege siege){
		this.rame = rame;
		this.wagon = wagon;
		this.siege = siege;
	}
	
	public String toStrign(){
		return "Wagon n°" + wagon.getId() + " Siege n°" + siege.getId();
	}
}
