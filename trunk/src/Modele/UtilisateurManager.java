package Modele;

import java.util.Vector;

public abstract class UtilisateurManager {
	private static Vector<Utilisateur> lesUtilisateurs = new Vector<Utilisateur> ();

	/**
	 * Ajout d'un utilisateur dans la liste des utilisateurs
	 * @param u
	 */
	public static void addUser (Utilisateur u) {
		lesUtilisateurs.add(u);
	}
	
	/**
	 * getUser permet de trouver un utilisateur dans la liste des utilisateurs
	 * @param login : le login dont on veut obtenir l'utilisateur
	 * @return l'utilisateur trouvé (null le cas échéant)
	 */
	public static Utilisateur getUser (String login) {
		for (Utilisateur u : lesUtilisateurs) {
			if (!(u.getLogin().equals(login))) continue;
			return u;
		}
		return null;
	}
	
	/**
	 * estProf renvoie vrai si l'utilisateur choisi est un professeur. Si
	 * l'utilisateur n'est pas trouvé, elle renvoie faux par défaut.
	 * @param login : le login de l'utilisateur dont on veut savoir s'il est professeur
	 * @return vrai ou faux suivant la catégorie
	 */
	public static boolean estProf (String login) {
		for (Utilisateur u : lesUtilisateurs) {
			if (!(u.getLogin().equals(login))) continue;
			return u.estProf();
		}
		return false;
	}
}
