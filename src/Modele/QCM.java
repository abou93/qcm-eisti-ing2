package Modele;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.DOMBuilder;
import org.jdom.output.DOMOutputter;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;


public class QCM {
	private String nom;
	private int difficulte;
	private int score;
	private int temps;
	private Date date;
	private ArrayList<Question> lesQuestions;
	private org.w3c.dom.Document xml;
	private int id; /*Hibernate*/
	private int id_cours; /*Hibernate*/
	
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
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_cours() {
		return id_cours;
	}

	public void setId_cours(int id_cours) {
		this.id_cours = id_cours;
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
		try{
            Element noeudQCM = doc.getRootElement();
            this.difficulte = Integer.parseInt(noeudQCM.getAttributeValue("lvl"));
            this.temps = Integer.parseInt(noeudQCM.getAttributeValue("temps"));
            if(noeudQCM.getAttributeValue("score") != null)
            {
            	this.score = Integer.parseInt(noeudQCM.getAttributeValue("score"));
            }
            if(noeudQCM.getAttributeValue("date") != null)
            {
            	this.date = new SimpleDateFormat("dd/mm/yyyy HH:ss").parse(noeudQCM.getAttributeValue("date"));
            }
            
            List<Element> lesNoeudsQuestion = noeudQCM.getChildren("question");
            for(int i=0 ; i<lesNoeudsQuestion.size() ; i++)
            {
            	this.addQuestion(new Question(lesNoeudsQuestion.get(i).getChildText("expression")));
                List<Element> lesNoeudsReponse = lesNoeudsQuestion.get(i).getChildren("reponse");
                for(int j = 0; j < lesNoeudsReponse.size(); j++)
                {
                	if(lesNoeudsReponse.get(j).getAttributeValue("select") != null)
                	{
                		this.getQuestion(i).addReponse(new Reponse(lesNoeudsReponse.get(j).getText(), lesNoeudsReponse.get(j).getAttributeValue("value"), lesNoeudsReponse.get(j).getAttributeValue("select")));
                	}
                	else
                	{
                		this.getQuestion(i).addReponse(new Reponse(lesNoeudsReponse.get(j).getText(), lesNoeudsReponse.get(j).getAttributeValue("value")));
                	}
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
	public void setXML()
	{
			this.date = new Date();
	        Element racine = new Element("qcm");
	        racine.setAttribute("temps", String.valueOf(this.getTemps()));
	        racine.setAttribute("lvl", String.valueOf(this.getDifficulte()));
	        racine.setAttribute("score", String.valueOf(this.getScore()));
	        racine.setAttribute("date", new SimpleDateFormat("dd/MM/yyyy HH:ss").format(date));
	        
	        int i,j;
	        for (i=0; i<lesQuestions.size(); i++)
	        {
	        	ArrayList<Element> questions = new ArrayList<Element>();
	            Element NoeudQuestion = new Element("question");
	            
	            Element NoeudExpression = new Element("expression");
	            NoeudExpression.setText(lesQuestions.get(i).getExpression());
	            
	            NoeudQuestion.addContent(NoeudExpression);
	            
	            for(j=0; j<lesQuestions.get(i).getLesReponses().size(); j++)
	            {
	            	ArrayList<Element> reponses = new ArrayList<Element>();
	            	Element NoeudReponse = new Element("reponse");
	            	NoeudReponse.setAttribute("value", String.valueOf(lesQuestions.get(i).getLesReponses().get(j).isValeur()));
	            	NoeudReponse.setAttribute("select", String.valueOf(lesQuestions.get(i).getLesReponses().get(j).isSelect()));
	            	NoeudReponse.setText(lesQuestions.get(i).getLesReponses().get(j).getExpression());
	            	
	            	reponses.add(NoeudReponse);
	            	NoeudQuestion.addContent(reponses);
	            }
	            questions.add(NoeudQuestion);
	            racine.addContent(questions);
	        }

				Document JDOMxml = new Document(racine);
	            DOMOutputter domOutputter = new DOMOutputter();
	            try {
					this.xml = domOutputter.output(JDOMxml);
				} catch (JDOMException e) {
					e.printStackTrace();
				}

	        
	       
	}
	
	
	public void readXML(String file)
	{
		try {
			javax.xml.parsers.DocumentBuilderFactory dbf = javax.xml.parsers.DocumentBuilderFactory.newInstance();
			javax.xml.parsers.DocumentBuilder db = dbf.newDocumentBuilder();
			InputSource is = new InputSource( new StringReader( file ) );
			this.xml = db.parse(is);
			this.readXML();
		} 
		catch (ParserConfigurationException e) {
			e.printStackTrace();			
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
