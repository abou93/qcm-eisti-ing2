package Modele;

import java.util.*;


public class Question {

	private String expression;
	private ArrayList<Reponse> reponses;
	
	public Question (String expression) {
		this.expression = expression;
		this.expression=this.expression.replaceAll(">", "&gt;");
		this.expression=this.expression.replaceAll("<", "&lt;");
		this.reponses = new ArrayList<Reponse> ();
	}
	
	public void addReponse (Reponse rep) {
		this.reponses.add(rep);
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
	
	public ArrayList<Reponse> getLesReponses() {
		return this.reponses;
	}
	
	public int getNbReponses () {
		return this.reponses.size();
	}
	
	public boolean aRepondu() {
		for (int i = 0; i < this.reponses.size(); i++)
		{
			if (this.reponses.get(i).isSelect() == true)
				return true;
		}
		return false;
	}
	
	public boolean evaluerReponses() {
		boolean correct = true;
		for (int i = 0; i < this.reponses.size(); i++)
		{
			correct = correct && this.reponses.get(i).evaluate();
		}
		return correct;
	}
}
