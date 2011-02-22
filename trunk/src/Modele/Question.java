package Modele;

import java.util.*;


public class Question {

	private String question;
	private Vector<String> reponses;
	private int numeroReponse;
	
	public Question (String texte) {
		this.question = texte;
		this.reponses = new Vector<String> ();
	}
	
	public void addReponse (String rep) {
		this.reponses.add(rep);
	}
	
	public void setBonneReponse (int i) {
		this.numeroReponse = i;
	}
	
	public String getBonneReponse() {
		return this.getReponse(this.getReponse());
	}
	public String getQuestion () {
		return this.question;
	}
	
	public String getReponse (int i) {
		return this.reponses.get(i);
	}
	
	public int getReponse () {
		return this.numeroReponse;
	}
	
	public int getNbReponse () {
		return this.reponses.size();
	}
}
