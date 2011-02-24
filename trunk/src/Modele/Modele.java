package Modele;

import java.io.*;
import java.util.*;

import org.jdom.*;
import org.jdom.input.*;

public class Modele
{
	private String QCMCourant;
	private ArrayList<Cours> lesCours;
	
	public Modele(String urlData) {
		this.QCMCourant = null;
        this.lesCours = new ArrayList<Cours> ();
        
    	Document doc;
    	SAXBuilder sxb = new SAXBuilder();
		try{
            doc = sxb.build(new File(urlData));
            Element noeudRacine = doc.getRootElement();
            List<Element> lesNoeudsCours = noeudRacine.getChildren("cours");
            for(int i=0 ; i<lesNoeudsCours.size() ; i++)
            {
            	this.lesCours.add(new Cours(lesNoeudsCours.get(i).getAttributeValue("name")));
                List<Element> lesNoeudsQCM = lesNoeudsCours.get(i).getChildren("qcm");
                for(int j=0 ; j<lesNoeudsQCM.size() ; j++)
                {
                	this.lesCours.get(i).getLesQCMs().add(new QCM(lesNoeudsQCM.get(j).getChildText("name-qcm"), Integer.parseInt(lesNoeudsQCM.get(j).getChildText("lvl-qcm"))));
                }
            }
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	public String getNomQCMCourant() {
		return this.QCMCourant;
	}
	public void setNomQCMCourant(String leQCM) {
		this.QCMCourant = leQCM;
	}
	public ArrayList<Cours> getLesCours() {
		return this.lesCours;
	}
	public void setLesCours(ArrayList<Cours> lesCours) {
		this.lesCours = lesCours;
	}
	public QCM get(String name) {
		for(int i = 0; i < this.lesCours.size(); i++)
		{
			for(int j = 0; j < this.getLesCours().get(i).getLesQCMs().size(); j++)
			{
				if(this.getLesCours().get(i).getLesQCMs().get(j).getNom().matches(name))
					return this.getLesCours().get(i).getLesQCMs().get(j);
			}
		}
		return null;
	}
	public QCM getQCMCourant() {
		for(int i = 0; i < this.lesCours.size(); i++)
		{
			for(int j = 0; j < this.getLesCours().get(i).getLesQCMs().size(); j++)
			{
				if(this.getLesCours().get(i).getLesQCMs().get(j).getNom().matches(this.QCMCourant))
					return this.getLesCours().get(i).getLesQCMs().get(j);
			}
		}
		return null;
	}
	
	public void initialiser (String qcmName, String urlData)
	{        
    	Document doc;
        SAXBuilder sxb = new SAXBuilder();
		try{
            doc = sxb.build(new File(urlData));
            Element noeudQCM = doc.getRootElement();
            List<Element> lesNoeudsQuestion = noeudQCM.getChildren("question");
            for(int i=0 ; i<lesNoeudsQuestion.size() ; i++)
            {
            	this.get(qcmName).addQuestion(new Question(lesNoeudsQuestion.get(i).getChildText("expression")));
                List<Element> lesNoeudsReponse = lesNoeudsQuestion.get(i).getChildren("reponse");
                for(int j = 0; j < lesNoeudsReponse.size(); j++)
                {
                	this.get(qcmName).getQuestion(i).addReponse(new Reponse(lesNoeudsReponse.get(j).getText(), lesNoeudsReponse.get(j).getAttributeValue("value")));
                }
            }
            
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
		this.QCMCourant = this.get(qcmName).getNom();
	}
	
	public void enregistrer(String urlResultat)
	{
		
	}
}