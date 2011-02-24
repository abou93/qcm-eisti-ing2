package Modele;

import java.util.Vector;

public abstract class UtilisateurManager {
	private static Vector<Utilisateur> lesUtilisateurs = new Vector<Utilisateur> ();

	public static void addUser (Utilisateur u) {
		lesUtilisateurs.add(u);
	}
	
	public static Utilisateur getUser (String login) {
		for (Utilisateur u : lesUtilisateurs) {
			if (!(u.getLogin().equals(login))) continue;
			return u;
		}
		return null;
	}
}
