package defaut;

import donnee.Donnees;
import gui.MainFrame;
import offre.Comparateur;
import utilisateur.Client;

/**
 * @author Vivien Galuchot - Vincent Hernandez Info 4
 * Février 2016, Projet POO
 * 
 * Gestion des données, enregistrements
 */
public class MainActivity {
	public static Client current = null;
	
	public static Client connect(String login, String motDePasse){
		current = Donnees.getInstance().findClient(login, motDePasse);
		return current;
	}
	
	public static void disconnect(){
		current = null;
	}
	
	public static void main(String[] args) {
		Donnees.setFichier("database.yml");
		Donnees.charger();
		
		Comparateur.buildGraph(Donnees.getTrains()); 
		
		MainFrame F = new MainFrame();
		F.setVisible(true);
	}
}