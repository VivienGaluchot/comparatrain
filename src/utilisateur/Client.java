package utilisateur;

import donnee.Donnees;

public class Client extends Personne{
	public static Client current = null;
	public static Client connect(String login, String motDePasse){
		current = Donnees.getInstance().findClient(login, motDePasse);
		return current;
	}
	public static void disconnect(){
		current = null;
	}
	
	private String login;
	private String motDePasse;
	
	public Client(){
		super();
		setId(null);
		setLogin(null);
		setMotDePasse(null);
	}
	
	public Client(Integer id){
		super();
		this.setId(id);
		setLogin(null);
		setMotDePasse(null);
	}
	
	public String toString(){
		String res = getLogin() + " : " + getPrenom() + " " + getNom();
		return res;
	}

	// Login
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	// MotDePasse
	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
}
