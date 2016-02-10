package defaut;

import comparaison.Comparateur;
import donnee.Donnees;

import gui.MainFrame;

public class classMain {
	public static void main(String[] args) {
		Donnees.setFichier("database.yml");
		Donnees.charger();
		Donnees.setFichier("databaseSave.yml");
		Donnees.sauvegarder();
		
		Comparateur.buildGraph(); 
		
		MainFrame F = new MainFrame();
		F.setVisible(true);
		
//		Rame r = new Rame(0);
//		System.out.println(r);
	}
}