package Modele;

public class Utilisateur {
	private String login;
	private String password;
	
	public Utilisateur (String log, String pwd) {
		this.login = log;
		this.password = pwd;
		UtilisateurManager.addUser(this);
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	
}
