package defaut;

import comparaison.Comparateur;
import gui.MaFenetre;

public class classMain {

	public static void main(String[] args) {
		Donnees data = new Donnees();
		
		data.charger("database.yml");
		data.afficher();
		
		Comparateur comp = new Comparateur(data);
		
		MaFenetre F = new MaFenetre("Comparateur",comp);
		F.setVisible(true);
		
//		Preference P1 = new Preference("Paris","18/12/2015 15h30", "Marseille", "18/12/2015 16h17");
//		System.out.println("Comparaison P1 :");
//		comp.comparer(P1).afficher();
//		
//		Preference P2 = new Preference();
//		P2.setGares("Marseille", "Nantes");
//		P2.setHArrive("07/01/2016 21h00");
//		System.out.println("\nComparaison P2 :");
//		comp.comparer(P2).afficher();
//		
//		Preference P3 = new Preference();
//		P3.setGares("Marseille", "Nantes");
//		P3.setHDepart("06/01/2016 16h15");
//		System.out.println("\nComparaison P3 :");
//		comp.comparer(P3).afficher();
//		
//		Preference P4 = new Preference();
//		P4.setGares("Avignon", "Nantes");
//		P4.setHDepart("08/01/2016 16h15");
//		System.out.println("\nComparaison P4 :");
//		comp.comparer(P4).afficher();
//		
//		Preference P5 = new Preference();
//		P5.setGares("Lyon", "Paris");
//		P5.setHDepart("06/01/2016 16h15");
//		System.out.println("\nComparaison P5 :");
//		comp.comparer(P5).afficher();
//		
//		Rame r = new Rame(0);
//		System.out.println(r);
	}
}