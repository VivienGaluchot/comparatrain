package comparatrain;

import java.time.LocalDateTime;

public class classMain {

	public static void main(String[] args) {
		
		Gare G1 = new Gare("Marseille");
		Gare G2 = new Gare("Paris");
		Gare G3 = new Gare("Lyon");
		Gare G4 = new Gare("Grenoble");
		Gare G5 = new Gare("Nante");
		
		LocalDateTime D1 = LocalDateTime.of(2015, 12, 18, 16, 30);
		LocalDateTime D2 = LocalDateTime.of(2015, 12, 19, 17, 30);
		LocalDateTime D3 = LocalDateTime.of(2015, 12, 20, 18, 30);
		LocalDateTime D4 = LocalDateTime.of(2015, 12, 21, 19, 30);
		LocalDateTime D5 = LocalDateTime.of(2015, 12, 22, 20, 30);
		LocalDateTime D6 = LocalDateTime.of(2015, 12, 23, 21, 30);
		LocalDateTime D7 = LocalDateTime.of(2015, 12, 24, 22, 30);
				
		
		Train T1 = new Train(1,G1,G2,D1,D2);
		Train T2 = new Train(2,G4,G2,D3,D4);
		Train T3 = new Train(3,G2,G2,D4,D6);
		Train T4 = new Train(4,G4,G1,D2,D4);
		Train T5 = new Train(5,G3,G1,D3,D7);
		
		Preference P1 = new Preference(G1, G3, D1, D2);
		Preference P2 = new Preference(G1, G2, D1, D2);
		Preference P3 = new Preference(G5, G4, D4, D7);
		Preference P4 = new Preference(G1, G4, D1, D4);
		Preference P5 = new Preference(G4, G3, D3, D5);
		
		Comparateur Comp = new Comparateur();
		Comp.ajoutertrain(T1);
		Comp.ajoutertrain(T2);
		Comp.ajoutertrain(T3);
		Comp.ajoutertrain(T4);
		Comp.ajoutertrain(T5);
		

	}

}
