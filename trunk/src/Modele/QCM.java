package Modele;

import java.util.*;


public class QCM {
	private String nom;
	private int difficulte;
	private int score;
	private ArrayList<Question> lesQuestions;
	
	public QCM (String name, int lvl) {
		this.nom = name;
		this.difficulte = lvl;
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

	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void clear(){
		lesQuestions.clear();
	}
	
	public Question getQuestion (int i) {
		return this.lesQuestions.get(i);
	}
	
	public ArrayList<Question> getLesQuestions () {
		return this.lesQuestions;
	}
	
	public void setLesQuestions(ArrayList <Question> questions) {
		this.lesQuestions = questions;
	}
	
	public void evaluerScore() {
		for (int i = 0; i < this.lesQuestions.size(); i++)
		{
			if (this.lesQuestions.get(i).evaluerReponses())
				this.score++;
		}
	}
}
