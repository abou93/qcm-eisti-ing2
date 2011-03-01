package Modele;

public class Utilisateur {
	private String login;
	private String password;
	private boolean professeur;
	
	public Utilisateur (String log, String pwd, boolean prof) {
		this.login = log;
		this.password = pwd;
		this.professeur = prof;
		UtilisateurManager.addUser(this);
	}
	
	public boolean estProf () {
		return this.professeur;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}
	
	
}
