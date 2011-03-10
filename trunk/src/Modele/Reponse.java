package Modele;


public class Reponse 
{
	private String expression;
	private boolean valeur;
	private boolean select;
	
	public Reponse(String expression, String valeur)
	{
		this.expression = expression;
		this.expression=this.expression.replaceAll(">", "&gt;");
		this.expression=this.expression.replaceAll("<", "&lt;");
		if(valeur.matches("true"))
			this.valeur = true;
		else
			this.valeur = false;
		this.select = false;
	}
	public String getExpression() {
		return this.expression;
	}
	public boolean isTrue() {
		return this.valeur;
	}
	public void setSelect(boolean v) {
		this.select = v;
	}
	public boolean isSelect() {
		return this.select;
	}
	public boolean evaluate() {
		if(this.select == this.valeur)
			return true;
		else
			return false;
	}
}
