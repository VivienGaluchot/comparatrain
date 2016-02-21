package elements;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Objet représentant les préférences de recherche
 */
public class Preference {
	// Non optionnels
	private String lieuxDepart;
	private String lieuxArrivee;
	private Horaire hDepart;
	private Horaire hArrivee;
	// Optionnel
	private Boolean direct;
	private Integer nbPlace;
	private Integer classe;
	private Integer sens;
	private Integer cote;
	private Boolean bar;
	
	public Preference(){
		lieuxDepart = null;
		lieuxArrivee = null;
		hDepart = null;
		hArrivee = null;
		direct = null;
		nbPlace = null;
		classe = null;
		sens = null;
		cote = null;
		bar = null;
	}
	
	public Preference(String gD, Horaire hD, String gA, Horaire hA){
		lieuxDepart = gD;
		lieuxArrivee = gA;
		hDepart = hD;
		hArrivee = hA;
		// Optionnel
		direct = null;
		nbPlace = null;
		classe = null;
		sens = null;
		cote = null;
		bar = null;
	}
	
	// Getters
	public String getLieuxDepart() { return lieuxDepart; }

	public String getLieuxArrivee() { return lieuxArrivee; }
	
	public Horaire getHDepart() { return hDepart; }

	public Horaire getHArrivee() { return hArrivee; }

	public Boolean getDirect() { return direct; }
	public void setDirect(Boolean direct) { this.direct = direct; }

	public Integer getNbPlace() { return nbPlace; }

	public void setNbPlace(Integer nbPlace) { this.nbPlace = nbPlace; }

	public Integer getClasse() { return classe; }
	public void setClasse(Integer classe) { this.classe = classe; }

	public Integer getSens() { return sens;	}
	public void setSens(Integer sens) {	this.sens = sens; }

	public Integer getCote() { return cote;	}
	public void setCote(Integer cote) {	this.cote = cote; }

	public Boolean getBar() { return bar; }
	public void setBar(Boolean bar) { this.bar = bar; }
}
