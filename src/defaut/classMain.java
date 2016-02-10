package defaut;

import comparaison.Comparateur;
import donnee.Donnees;
import elements.Billet;
import gui.MainFrame;
import utilisateur.Client;

public class classMain {

	public static void main(String[] args) {
		Donnees.setFichier("database.yml");

		Donnees.charger();
		try {
			Donnees.billets.add(new Billet(null, Donnees.clients.get(0)));
		} catch (Erreur e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Donnees.getInstance().sauvegarder("databaseSave.yml");
		
		Comparateur.buildGraph(); 
		
		MainFrame F = new MainFrame();
		F.setVisible(true);
		
//		Rame r = new Rame(0);
//		System.out.println(r);
	}
}