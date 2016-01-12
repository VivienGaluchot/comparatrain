package modele;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import comparaison.Preference;
import defaut.Erreur;

public class Horaire implements Evaluable<Horaire>{
	LocalDateTime time;
	
	String parseJour = "dd'/'MM'/'yyyy";
	String parseHeure = "HH'h'mm";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern(parseJour + " " + parseHeure);
	
	public Horaire(LocalDateTime t){
		time = t;
	}
	
	public Horaire(String s) throws Erreur{
		try{
			time = LocalDateTime.parse(s, formatter);
		}
		catch (DateTimeParseException e){
			throw new Erreur(2);
		}
	}
	
	public String toString(){
		return time.format(formatter);
	}
	
	public int compareTo(Horaire h){
		return time.compareTo(h.time);
	}
	
	public double eval(Horaire h) {
		double res = 1;			
		
		long dSec = time.until( h.time, ChronoUnit.SECONDS);
		
		//res = 1 pour pour dSec = 24*60*60 secondes (1jour), res proportionnel a dSec
		res = (double)dSec / (24*60*60);
		
		// res = 1 pour dSec = 0 | 0.5 pour dSec = 24*60*60 | 0 en l'infini
		res = 1/(Math.abs(res) + 1);
		return res;
	}
}
