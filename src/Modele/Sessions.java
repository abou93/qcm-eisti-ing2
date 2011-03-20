package Modele;

import java.util.Vector;

public abstract class Sessions {
	private static Vector<String> lesSessions = new Vector<String> ();
	
	public static void connecter (String s) {
		lesSessions.add(s);
	}
	
	public static boolean estConnecte (String s) {
		return lesSessions.contains(s);
	}
	
	public static void deconnecter (String s) {
		lesSessions.remove(s);
	}
}
