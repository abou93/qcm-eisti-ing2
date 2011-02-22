package Modele;

import org.jdom.Element;

public class Reponse 
{
	private String reponse;
	private String id;
	private boolean valeur;
	
	public Reponse(Element e)
	{
		reponse = e.getValue();
		id = e.getAttributeValue("id");
		if(e.getAttributeValue("is") != null && e.getAttributeValue("is").matches("true"))
		{
			valeur = true; 
		}
		else { valeur = false; }
	}
	public String toString()
	{
		return "Reponse ("+id+") : "+reponse+" --"+valeur+"--";
	}
	public String getReponse() {
		return reponse;
	}
	public String getId() {
		return id;
	}
	public boolean isTrue() {
		return valeur;
	}
	
}
