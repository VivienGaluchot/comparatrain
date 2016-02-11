package defaut;

import comparaison.Comparateur;
import donnee.Donnees;

import gui.MainFrame;

public class classMain {
	public static void main(String[] args) throws Erreur {
		Donnees.setFichier("database.yml");
		Donnees.charger();
		
		Comparateur.buildGraph(); 
		
		MainFrame F = new MainFrame();
		F.setVisible(true);
	}
}