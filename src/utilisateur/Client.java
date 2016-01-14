package utilisateur;

public class Client extends Personne{
	private Integer id;
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
		String res = "Client n°" + getId() + " : " + getLogin();
		return res;
	}
	
	// Id
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
