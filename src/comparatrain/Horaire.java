package comparatrain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Horaire{
	LocalDateTime time;
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH'h'mm");
	
	public Horaire(LocalDateTime t){
		time = t;
	}
	
	public Horaire(String s){
		time = LocalDateTime.parse(s, formatter);
	}
	
	public String toString(){
		return time.format(formatter);
	}
	
	public String jourToString(){
		DateTimeFormatter formatterJ = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy");
		return time.format(formatterJ);
	}
	
	public String heureToString(){
		DateTimeFormatter formatterJ = DateTimeFormatter.ofPattern("HH'h'mm");
		return time.format(formatterJ);
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
