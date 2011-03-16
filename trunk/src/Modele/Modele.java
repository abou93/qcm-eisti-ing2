package Modele;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.*;




public class Modele
{
	private String QCMCourant;
	private ArrayList<Cours> lesCours;
	private QCM resultatCourant;
	
	public Modele(String urlData) {
        this.lesCours = new ArrayList<Cours>();
        this.resultatCourant = new QCM("Resultat Vide", 0);
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
			for(int j = 0; j < this.lesCours.get(i).getLesQCMs().size(); j++)
			{
				if(this.lesCours.get(i).getLesQCMs().get(j).getNom().matches(name))
					return this.getLesCours().get(i).getLesQCMs().get(j);
			}
		}
		return null;
	}
	
	public QCM getQCMCourant() {
		if(this.get(this.QCMCourant) == null)
			return null;
		else
			return this.get(this.getNomQCMCourant());
	}

	public void setReponse(int indexQ, int indexR, boolean v) {
		this.getQCMCourant().getQuestion(indexQ).getReponse(indexR).setSelect(v);
	}
	
	public void chargerQCM (String qcmName, String urlData)
	{
        this.get(qcmName).clear();
    	Document doc;
        SAXBuilder sxb = new SAXBuilder();
		try{
            doc = sxb.build(new File(urlData));
            Element noeudQCM = doc.getRootElement();
            List<Element> lesNoeudsQuestion = noeudQCM.getChildren("question");
            this.get(qcmName).setTemps(Integer.parseInt(noeudQCM.getAttributeValue("temps")));
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
	
	public void evaluerScoreQCM() {
		this.getQCMCourant().evaluerScore();
	}
	
	public void enregistrerResultat(String urlData) throws ParserConfigurationException, SAXException, IOException
	{
		Document doc;
		XMLOutputter sortie;
		try{
			sortie = new XMLOutputter(Format.getPrettyFormat());
			Element noeudRacine = new Element("qcm-resultat");
			doc = new Document(noeudRacine);
			Element noeudScore = new Element("score");
			noeudScore.setText(String.valueOf(this.getQCMCourant().getScore()));
			noeudRacine.addContent(noeudScore);
			for (int i = 0; i < this.getQCMCourant().getLesQuestions().size(); i++)
			{
				Element noeudQuestion = new Element("question");
				for (int j = 0; j < this.getQCMCourant().getQuestion(i).getLesReponses().size(); j++)
				{
					Element noeudExpression = new Element("expression");
					Element noeudReponse = new Element("reponse");
					Attribute attributReponse;
					if(this.getQCMCourant().getQuestion(i).getLesReponses().get(j).isSelect())
					{
						noeudExpression.setText(this.getQCMCourant().getQuestion(i).getExpression());
						noeudQuestion.addContent(noeudExpression);
						attributReponse = new Attribute("value", String.valueOf(this.getQCMCourant().getQuestion(i).getReponse(j).isTrue()));
						noeudReponse.setAttribute(attributReponse);
						noeudReponse.setText(this.getQCMCourant().getQuestion(i).getReponse(j).getExpression());
						noeudQuestion.addContent(noeudReponse);
					}
				}
				noeudRacine.addContent(noeudQuestion);
			}
			File dir = new File(urlData); 
			System.out.println(dir.getAbsolutePath());
			System.out.println("Mkdir = "+dir.mkdir());
			File f = new File(dir.getAbsolutePath()+
					File.separator + "Resultat_" + 
					this.getQCMCourant().getNom() + ".xml"); 
			FileOutputStream fos = new FileOutputStream(f); 
			sortie.output(doc, fos);
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void chargerResultat (String resultatName, String urlData)
	{
        this.resultatCourant.clear();
        this.resultatCourant.setNom(resultatName);
    	Document doc;
        SAXBuilder sxb = new SAXBuilder();
		try{
            doc = sxb.build(new File(urlData));
            Element noeudQCMResultat = doc.getRootElement();
            //Element noeudDifficulte = noeudQCMResultat.getChild("difficulte");
            //this.resultatCourant.setDifficulte(Integer.parseInt(noeudDifficulte.getText()));
            Element noeudScore = noeudQCMResultat.getChild("score");
            this.resultatCourant.setScore(Integer.parseInt(noeudScore.getText()));
            List<Element> lesNoeudsQuestion = noeudQCMResultat.getChildren("question");
            for(int i=0 ; i<lesNoeudsQuestion.size() ; i++)
            {
            	this.resultatCourant.addQuestion(new Question(lesNoeudsQuestion.get(i).getChildText("expression")));
                List<Element> lesNoeudsReponse = lesNoeudsQuestion.get(i).getChildren("reponse");
                for(int j = 0; j < lesNoeudsReponse.size(); j++)
                {
                	this.resultatCourant.getQuestion(i).addReponse(new Reponse(lesNoeudsReponse.get(j).getText(), lesNoeudsReponse.get(j).getAttributeValue("value")));
                }
            }
		}
		catch (Exception e){
			System.out.println("Erreur lors du chargement du Resultat");
			System.out.println(e.toString());
		}
	}
	public QCM getResultatCourant() {
		return resultatCourant;
	}
	
}