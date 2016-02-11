package elements;

public abstract class Indexable implements Comparable<Indexable>{
	private Integer id;
	
	public Indexable(){
		id = null;
	}
	
	public Indexable(Integer id){
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Indexable other = (Indexable) obj;
		return id == other.id;
	}
	
	public int compareTo(Indexable o){
		return getId() - o.getId();
	}
}
