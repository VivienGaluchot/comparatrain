package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Un objet de type Indexable est composé d'un identifiant entier
 */
public abstract class Indexable{
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
}
