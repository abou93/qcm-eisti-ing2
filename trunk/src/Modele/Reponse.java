package Modele;

import org.jdom.Element;

public class Reponse 
{
	private String expression;
	private boolean valeur;
	
	public Reponse(String expression, String valeur)
	{
		this.expression = expression;
		if(valeur.matches("true"))
			this.valeur = true;
		else
			this.valeur = false;
	}
	public String getExpression() {
		return this.expression;
	}
	public boolean isTrue() {
		return this.valeur;
	}
	
}
