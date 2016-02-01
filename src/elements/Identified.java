package elements;

public abstract class Identified {
	private Integer id;
	
	public Identified(){
		id = null;
	}
	
	public Identified(Integer id){
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
		Identified other = (Identified) obj;
		return id == other.id;
	}
}
