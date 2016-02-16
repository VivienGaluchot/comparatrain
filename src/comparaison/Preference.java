package comparaison;

import elements.Horaire;

/**
 * @author Vivien Galuchot - Vincent Hernandez
 *
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
	
	public Preference(){
		lieuxDepart = null;
		lieuxArrivee = null;
		hDepart = null;
		hArrivee = null;
		direct = null;
		nbPlace = null;
		classe = null;
	}
	
	public Preference(String gD, Horaire hD, String gA, Horaire hA, Boolean direct, Integer nbPlace, Integer classe, Integer cote, Integer sens){
		lieuxDepart = gD;
		lieuxArrivee = gA;
		hDepart = hD;
		hArrivee = hA;
		this.direct = direct;
		this.nbPlace = nbPlace;
		this.classe = classe;
	}
	
	// Getters
	public String getLieuxDepart() { return lieuxDepart; }

	public String getLieuxArrivee() { return lieuxArrivee; }
	
	public Horaire getHDepart() { return hDepart; }

	public Horaire getHArrivee() { return hArrivee; }

	public Boolean getDirect() { return direct; }

	public Integer getNbPlace() { return nbPlace; }

	public Integer getClasse() { return classe; }

	public Integer getSens() { return sens; }

	public Integer getCote() { return cote; }
}
