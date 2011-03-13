package Modele;

import java.util.ArrayList;
import java.util.List;

public class Cours {
	private int id; /*Hibernate*/
	private String nom;
	private List<QCM> lesQCMs;

	public Cours(){} /*Hibernate*/
	
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

	public List<QCM> getLesQCMs() {
		return lesQCMs;
	}

	public void setLesQCMs(List<QCM> lesQCMs) {
		this.lesQCMs = lesQCMs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
