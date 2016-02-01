package elements;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

import defaut.Erreur;

public class Horaire implements Evaluable<Horaire>, Comparable<Horaire>{
	
	private LocalDateTime time;	
	private String serialTime;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd'/'MM'/'yyyy HH'h'mm");
	DateTimeFormatter formatterCourt = DateTimeFormatter.ofPattern("dd'/'MM HH'h'mm");
	
	// Constructeurs
	
	public Horaire(){
		time = null;
		serialTime = null;
	}
	
	public Horaire(LocalDateTime t){
		time = t;
		serialTime = time.format(formatter);
	}
	
	public Horaire(String s) throws Erreur{
		setSerialTime(s);
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
	
	// Serialisation
	public String getSerialTime(){
		return serialTime;
	}
	
	public void setSerialTime(String s) throws Erreur{
		try{
			serialTime = s;
			time = LocalDateTime.parse(serialTime, formatter);
		}
		catch (DateTimeParseException e){
			throw new Erreur(2);
		}
	}
	
	public boolean estInit(){
		return time != null;
	}
	
	// Utilitaires
	public String toString(){
		return time.format(formatterCourt);
	}
	
	public String toStringLong(){
		return time.format(formatter);
	}
	
	public int compareTo(Horaire h){
		return time.compareTo(h.time);
	}
	
	/**
	 * Est connecté si est avant (o.horaire+d),
	 * moins de 5h avant o.horaire
	 */
	public boolean isConnectedTo(Horaire o, Duration d){
		LocalDateTime min = time.minus(d);
		// A FAIRE
		return true;
	}
	
	public Horaire minus(TemporalAmount amount){
		return new Horaire(time.minus(amount));
	}
}
