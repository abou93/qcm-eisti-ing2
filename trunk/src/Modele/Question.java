package Modele;

import java.util.*;

import org.jdom.Element;


public class Question {

	private String question;
	private ArrayList<Reponse> reponses;
	
	public Question (String texte) {
		this.question = texte;
		this.reponses = new ArrayList<Reponse> ();
	}
	
	public Question (Element e)
	{
		this.question = e.getChild("expression").getValue();
		System.out.println("Expression = "+this.question);
		
		this.reponses = new ArrayList<Reponse>();
		List<Element> reponses = e.getChildren("reponse");
		for(int i=0 ; i<reponses.size() ; i++)
		{
			Reponse r = new Reponse(reponses.get(i));
			this.reponses.add(r);
//			System.out.println(r);
		}
	}
	
	public String getQuestion () {
		return this.question;
	}
	
	public Reponse getReponse (int i) {
		return this.reponses.get(i);
	}
	
	public Reponse getReponse () {
		return this.getReponses().get(0);
	}
	public ArrayList<Reponse> getReponses () {
		ArrayList<Reponse> bonnesReponses = new ArrayList<Reponse>();
		for(int i=0 ; i<reponses.size() ; i++)
		{
			if(reponses.get(i).isTrue())
			{
				bonnesReponses.add(reponses.get(i));
			}
		}
		return bonnesReponses;
	}
	
	public int getNbReponse () {
		return this.reponses.size();
	}
}
