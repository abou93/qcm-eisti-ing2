package modele;

import java.util.Vector;

public class Personne {

	private String nom;
	private String prenom;
	private String sexe;
	private String codePostal;
	private Vector<String> transport;
	
	public Personne (String n, String p, String s, String c, String [] t) {
		this.nom = n;
		this.prenom = p;
		this.sexe = s;
		this.codePostal = c;
		if (t != null) {
			this.transport = new Vector<String> ();
			for (String tr : t) {
				this.transport.add(tr);
			}
		}
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public Vector<String> getTransport() {
		return transport;
	}
	
}
