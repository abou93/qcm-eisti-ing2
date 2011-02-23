package Modele;

import java.util.ArrayList;

public class Cours {
	private String nom;
	private ArrayList<QCM> lesQCMs;

	public Cours(String name) {
		this.nom = name;
		this.lesQCMs = new ArrayList<QCM>();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public ArrayList<QCM> getLesQCMs() {
		return lesQCMs;
	}

	public void setLesQCMs(ArrayList<QCM> lesQCMs) {
		this.lesQCMs = lesQCMs;
	}

}
