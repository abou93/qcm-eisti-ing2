package Modele;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom.Element;
import org.jdom.input.DOMBuilder;
import org.jdom.input.SAXBuilder;
import org.jdom.output.DOMOutputter;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;


public class QCM {
	private String nom;
	private int difficulte;
	private int score;
	private int temps;
//	private Date date;
	private ArrayList<Question> lesQuestions;
	private org.w3c.dom.Document xml;
	private int id; /*Hibernate*/
	
	public QCM (){
		this.lesQuestions = new ArrayList<Question> ();
	} /*Hibernate*/
	
	public QCM (String nom, int lvl) {
		this.nom = nom;
		this.difficulte = lvl;
		this.lesQuestions = new ArrayList<Question> ();
	}
	
	public int addQuestion (Question q) {
		this.lesQuestions.add(q);
		return this.lesQuestions.indexOf(q);
	}
	
	public int getNbQuestions () {
		return this.lesQuestions.size();
	}
	
	public int getDifficulte () {
		return this.difficulte;
	}
	
	public void setDifficulte (int diff) {
		this.difficulte = diff;
	}
	
	public String getNom () {
		return this.nom;
	}
	
	public void setNom (String n) {
		this.nom = n;
	}

	public int getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public void clear(){
		lesQuestions.clear();
	}
	
	public Question getQuestion (int i) {
		return this.lesQuestions.get(i);
	}
	
	public ArrayList<Question> getLesQuestions () {
		return this.lesQuestions;
	}
	
	public void setLesQuestions(ArrayList <Question> questions) {
		this.lesQuestions = questions;
	}
	
	public void evaluerScore() {
		for (int i = 0; i < this.lesQuestions.size(); i++)
		{
			if (this.lesQuestions.get(i).evaluerReponses())
				this.score++;
		}
	}
	
	public void setTemps (int tps) {
		this.temps = tps;
	}
	
	public int getTemps () {
		return this.temps;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public org.w3c.dom.Document getXml() {
		return xml;
	}

	public void setXml(org.w3c.dom.Document xml) {
		this.xml = xml;
	}
	
	public String getXmlText()
	{
		DOMBuilder builder = new DOMBuilder();
		org.jdom.Document documentJDOM = builder.build(xml);
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
	    return sortie.outputString(documentJDOM);	
	}
	
	public void readXML() {
		DOMBuilder builder = new DOMBuilder();
		org.jdom.Document doc = builder.build(this.xml);
		
		SAXBuilder sxb = new SAXBuilder();
		try{
            Element noeudQCM = doc.getRootElement();
            this.difficulte = Integer.parseInt(noeudQCM.getAttributeValue("lvl"));
            this.temps = Integer.parseInt(noeudQCM.getAttributeValue("temps"));
//            this.lesQuestions = new ArrayList<Question>();
            
            List<Element> lesNoeudsQuestion = noeudQCM.getChildren("question");
            for(int i=0 ; i<lesNoeudsQuestion.size() ; i++)
            {
            	this.addQuestion(new Question(lesNoeudsQuestion.get(i).getChildText("expression")));
                List<Element> lesNoeudsReponse = lesNoeudsQuestion.get(i).getChildren("reponse");
                for(int j = 0; j < lesNoeudsReponse.size(); j++)
                {
                	this.getQuestion(i).addReponse(new Reponse(lesNoeudsReponse.get(j).getText(), lesNoeudsReponse.get(j).getAttributeValue("value")));
                }
            }
            
            DOMOutputter domOutputter = new DOMOutputter();
            this.xml = domOutputter.output(doc);
		}
		catch (Exception e){
			System.out.println(e.toString());
			System.out.println("Probleme lors de la lecture du xml du QCM");
		}
	}
}
