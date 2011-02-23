package Modele;

import java.util.*;


public class QCM {
	private String nom;
	private int difficulte;
	private String url;
	private ArrayList<Question> lesQuestions;
	
	public QCM (String name, int lvl, String url) {
		this.nom = name;
		this.difficulte = lvl;
		this.url = url;
		this.lesQuestions = new ArrayList<Question> ();
	}
	
	public int addQuestion (Question q) {
		this.lesQuestions.add(q);
		return this.lesQuestions.indexOf(q);
	}
	
	public int getNbQuestions () {
		return this.lesQuestions.size();
	}
	
	public int getDifficulte () {
		return this.difficulte;
	}
	
	public void setDifficulte (int diff) {
		this.difficulte = diff;
	}
	
	public String getNom () {
		return this.nom;
	}
	
	public void setNom (String n) {
		this.nom = n;
	}
	
	public Question getQuestion (int i) {
		return this.lesQuestions.get(i);
	}
	
	public ArrayList<Question> getLesQuestions () {
		return this.lesQuestions;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
