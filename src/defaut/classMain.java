package defaut;

import comparaison.Comparateur;
import donnee.Donnees;

import gui.MainFrame;
import train.Rame;

public class classMain {
	public static void main(String[] args) {
		Donnees.setFichier("database.yml");
		Donnees.charger();
		
		Comparateur.buildGraph(); 
		
		MainFrame F = new MainFrame();
		F.setVisible(true);
		
		// MARCHE PAS :(
		Rame r = Donnees.rames.get(20);
		Donnees.trains.get(5650).setRame(r);

		Donnees.setFichier("databaseSave.yml");
		Donnees.sauvegarder();
	}
}