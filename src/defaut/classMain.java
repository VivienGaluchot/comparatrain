package defaut;

import comparaison.Comparateur;
import donnée.Donnees;
import gui.MainFrame;

public class classMain {

	public static void main(String[] args) {
		Donnees.setFichier("database.yml");

		Donnees.charger();
//		Donnees.getInstance().sauvegarder("databaseSave.yml");
		
		Comparateur comp = Comparateur.getInstance();
		comp.setData(Donnees.getInstance());
		
		MainFrame F = new MainFrame();
		F.setVisible(true);
		
//		Rame r = new Rame(0);
//		System.out.println(r);
	}
}