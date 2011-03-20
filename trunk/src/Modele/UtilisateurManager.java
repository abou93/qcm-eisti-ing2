package Modele;

import java.math.BigDecimal;
import java.util.List;
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
			if (!(u.getUserLogin().equals(login))) continue;
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
			if (!(u.getUserLogin().equals(login))) continue;
			return u.estProf();
		}
		return false;
	}

	public static void creerListe(List<Utilisateur> result) {
		// TODO Auto-generated method stub
		for (int i = 0; i < result.size(); i++) {
			lesUtilisateurs.add((Utilisateur)result.get(i));
		}
	}
	
	public static int getId (String login) {
        for (Utilisateur u : lesUtilisateurs) {
                if (! u.getUserLogin().equals(login)) continue;
                return u.getUserId().intValue();
        }
        return -1;
	}

	public static Vector<Utilisateur> getLesUtilisateurs() {
		return lesUtilisateurs;
	}
	
	
}
