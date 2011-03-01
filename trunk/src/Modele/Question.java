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
	
	public ArrayList<Reponse> getBonnesReponses() {
		ArrayList<Reponse> BonnesReponses = new ArrayList<Reponse>();
		for(int i = 0; i < this.reponses.size(); i ++)
		{
			if(this.reponses.get(i).isTrue())
				BonnesReponses.add(i, this.reponses.get(i));
		}
		return BonnesReponses;
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
	
	public Reponse getBonneReponse(int i) {
		return this.getBonnesReponses().get(i);
	}
	
	public int getNbReponses () {
		return this.reponses.size();
	}
	
	public int getNbBonnesReponses() {
		return this.getBonnesReponses().size();
	}
}
