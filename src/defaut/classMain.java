package defaut;

import comparaison.Comparateur;
import donnée.Donnees;
import gui.MaFenetre;

public class classMain {

	public static void main(String[] args) {
		Donnees data = new Donnees();

		data.charger("simpleDatabase.yml");
//		data.charger("databaseSave.yml");
//		data.sauvegarder("databaseSave.yml");
		
		Comparateur comp = new Comparateur(data);
		
		MaFenetre F = new MaFenetre("ComparaTrain",comp);
		F.setVisible(true);
		
//		Rame r = new Rame(0);
//		System.out.println(r);
	}
}