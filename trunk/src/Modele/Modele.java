package Modele;

import java.util.*;
import java.io.*;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class Modele
{
	private ArrayList<QCM> lesQCMs;
	
	public Modele() 
	{
		try
		{
			Document doc;
            SAXBuilder sxb = new SAXBuilder();
            System.out.println("1");
            doc = sxb.build(new File("WebContent/data.xml"));
            System.out.println("2");
            Element racine = doc.getRootElement();
            List<Element> lesCours = racine.getChildren("cours");
            
            lesQCMs = new ArrayList<QCM>();
            for(int i=0 ; i<lesCours.size() ; i++)
            {
            	lesQCMs.add(new QCM(lesCours.get(i).getAttributeValue("nom"),
            						lesCours.get(i).getAttributeValue("lvl"),
            						lesCours.get(i)
            						)
            				);
            }
		}
		catch(Exception e){System.out.println("Erreur lors de la lecture de data.xml");}
        
	}
	
	
	public void initialiser (String nom)
	{
		QCM qcm = trouver(nom);
		if(qcm != null)
		{
			qcm.initQCM();
		}
		else{ System.out.println("Erreur lors de l'initialisation du QCM : "+qcm+".");}
	}
	public QCM trouver(String nom)
	{
		for(int i=0; i<lesQCMs.size() ; i++)
		{
			if(lesQCMs.get(i).getNom().matches(nom))
			{
				return lesQCMs.get(i);
			}
		}
		return null;
	}


	public ArrayList<QCM> getLesQCMs() {
		return lesQCMs;
	}
	
}