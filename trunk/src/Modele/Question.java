package Modele;

import java.util.*;


public class Question {

	private String expression;
	private ArrayList<Reponse> reponses;
	
	public Question (String expression) {
		this.expression = expression;
		this.reponses = new ArrayList<Reponse> ();
	}
	
	public void addReponse (Reponse rep) {
		this.reponses.add(rep);
	}
	
	public Reponse getBonneReponse() {
		for(int i = 0; i < this.reponses.size(); i ++)
		{
			if(this.reponses.get(i).isTrue())
				return this.reponses.get(i);
		}
		return null;
	}
	
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getExpression () {
		return this.expression;
	}
	
	public Reponse getReponse (int i) {
		return this.reponses.get(i);
	}
	
	public int getNbReponse () {
		return this.reponses.size();
	}
}
