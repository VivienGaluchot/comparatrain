package utilisateur;

public class Client extends Personne{
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
		String res = getId() + " - " + getLogin() + " : " + getPrenom() + " " + getNom();
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
