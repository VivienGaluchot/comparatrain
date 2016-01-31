package comparaison;

import java.util.ArrayList;
import java.util.TreeSet;

public class Resultat extends TreeSet<Offre>{
	private static final long serialVersionUID = 1L;
	
	public void ajouter(Offre t){
		add(t);
	}
	
	public void afficher(){
		if(this.isEmpty() || last().eval == 0) System.out.println("Aucun résultat");
		else{
			do{
				Offre t = pollLast();
				System.out.println(t);
			}while(!isEmpty());			
		}
	}
	
	public ArrayList<Offre> getMeilleurs(int n){
		ArrayList<Offre> res = new ArrayList<Offre>();
		
		if(this.isEmpty()) return res;
		
		int i = 0;
		do{
			Offre t = pollLast();
			res.add(t);
		}while(!isEmpty() && i<n);	
		
		return res;
	}
}