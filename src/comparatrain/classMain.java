package comparatrain;

public class classMain {

	public static void main(String[] args) {
		Comparateur Comp = new Comparateur();
		
		Preference P1 = new Preference("Paris","18/12/2015 15h30", "Marseille", "18/12/2015 16h17");
		System.out.println("\nComparaison P1 :");
		Comp.comparer(P1).afficher();
		
		Preference P2 = new Preference();
		P2.setGares("Marseille", "Nantes");
		P2.setHArrive("18/12/2015 21h00");
		System.out.println("\nComparaison P2 :");
		Comp.comparer(P2).afficher();
		
		Preference P3 = new Preference();
		P3.setGares("Marseille", "Nantes");
		P3.setHDepart("18/12/2015 16h15");
		System.out.println("\nComparaison P3 :");
		Comp.comparer(P3).afficher();
		
		Preference P4 = new Preference();
		P4.setGares("Avignon", "Nantes");
		P4.setHDepart("18/12/2015 16h15");
		System.out.println("\nComparaison P4 :");
		Comp.comparer(P4).afficher();
		
		Preference P5 = new Preference();
		P5.setGares("Lyon", "Paris");
		P5.setHDepart("18/12/2015 16h15");
		System.out.println("\nComparaison P5 :");
		Comp.comparer(P5).afficher();
	}
}