package comparatrain;

import java.time.LocalDateTime;

public class classMain {

	public static void main(String[] args) {
		
		Gare G1 = new Gare(1,"Marseille");
		Gare G2 = new Gare(2,"Paris");
		Gare G3 = new Gare(3,"Lyon");
		Gare G4 = new Gare(4,"Grenoble");
		Gare G5 = new Gare(5,"Nante");
		
		LocalDateTime D1 = LocalDateTime.of(2015, 12, 18, 14, 30);
		LocalDateTime D2 = LocalDateTime.of(2015, 12, 18, 17, 30);
		LocalDateTime D3 = LocalDateTime.of(2015, 12, 18, 15, 30);
		LocalDateTime D4 = LocalDateTime.of(2015, 12, 18, 19, 30);
		LocalDateTime D5 = LocalDateTime.of(2015, 12, 22, 20, 30);
		LocalDateTime D6 = LocalDateTime.of(2015, 12, 23, 21, 30);
		LocalDateTime D7 = LocalDateTime.of(2015, 12, 24, 22, 30);
				
		Train T1;
		Train T2;
		Train T3;
		Train T4;
		Train T5;
		Comparateur Comp = new Comparateur();
		
		try {
			T1 = new Train(1,G1,G2,D1,D2);
			System.out.println(T1);
			Comp.ajoutertrain(T1);
		} catch (Erreur e) {
			System.out.println("Erreur : " + e);
		}
		try {
			T2 = new Train(2,G1,G2,D3,D4);
			System.out.println(T2);
			Comp.ajoutertrain(T2);
		} catch (Erreur e) {
			System.out.println("Erreur : " + e);
		}
		try {
			// Train incohérent
			T3 = new Train(3,G2,G2,D4,D6);
			System.out.println(T3);
			Comp.ajoutertrain(T3);
		} catch (Erreur e) {
			System.out.println("Erreur : " + e);
		}
		try {
			T4 = new Train(4,G4,G1,D2,D4);
			System.out.println(T4);
			Comp.ajoutertrain(T4);
		} catch (Erreur e) {
			System.out.println("Erreur : " + e);
		}
		try {
			T5 = new Train(5,G3,G1,D3,D7);
			System.out.println(T5);
			Comp.ajoutertrain(T5);
		} catch (Erreur e) {
			System.out.println("Erreur : " + e);
		}
		
		Preference P1 = new Preference(G1, G2, D1, D2);
		Preference P2 = new Preference(G1, G2, D3, D4);
		Preference P3 = new Preference(G5, G4, D4, D7);
		Preference P4 = new Preference(G1, G4, D1, D4);
		Preference P5 = new Preference(G4, G1, D3, D5);
		
		System.out.println("\nComparaison P1 :");
		Comp.comparer(P1);
		System.out.println("Comparaison P2 :");
		Comp.comparer(P2);
		System.out.println("Comparaison P3 :");
		Comp.comparer(P3);
		System.out.println("Comparaison P4 :");
		Comp.comparer(P4);
		System.out.println("Comparaison P5 :");
		Comp.comparer(P5);
	}
}