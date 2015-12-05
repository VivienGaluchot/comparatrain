package comparatrain;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Horaire{
	LocalDateTime time;
	
	public Horaire(LocalDateTime t){
		time = t;
	}
	
	public String toString(){
		return time.toString();
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
