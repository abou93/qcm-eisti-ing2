package Modele;

import java.io.File;
import java.util.*;
import org.jdom.*;
import org.jdom.input.*;



public class QCM {
	private String nom;
	private String lvl;
	private Element e;
	private ArrayList<Question> lesQuestions;
	
	public QCM (String n, String lvl, Element e) {
		this.nom = n;
		this.lvl = lvl;
		this.lesQuestions = new ArrayList<Question> ();
		this.e = e;
	}
	
	public void initQCM()
	{		
        List<Element> questions = e.getChildren("question");
        
        ArrayList<Integer> NumQuestion = new ArrayList<Integer>();
        int random;
        for(int i=0 ; i<questions.size() ; i++)
        {
        	random = (int)(Math.random() * (questions.size()));
        	/* si on a deja selectionne la question */
        	if(NumQuestion.contains(random))
        	{
        		i--;
        	}
        	else
        	{
        		NumQuestion.add(random);
        		lesQuestions.add(new Question(questions.get(random)));
        	}
        }
	}

	public String getNom() {
		return nom;
	}

	public String getLvl() {
		return lvl;
	}

	public ArrayList<Question> getLesQuestions() {
		return lesQuestions;
	}
	
	public int getNbQuestions(){
		return lesQuestions.size();
	}
}
