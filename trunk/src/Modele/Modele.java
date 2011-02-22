package Modele;

import java.util.*;
import java.io.*;

public class Modele
{
	private QCM leQCM;
	private ArrayList<String> lesQCMs;
	private ArrayList<Integer> lesDifficultes;
	
	public Modele() {
		this.leQCM = null;
        this.lesQCMs = new ArrayList<String> ();
        this.lesDifficultes = new ArrayList<Integer> ();
        String ligne;
        
        /*lesQCMs.add("DROIT1");
        lesQCMs.add("HTML1");
        lesDifficultes.add(3);
        lesDifficultes.add(4);*/
		ligne = "/Application_L2/QCMs/QCMs.txt";
		try{
			InputStream ips = new FileInputStream(ligne); 
			InputStreamReader ipsr = new InputStreamReader(ips);
			BufferedReader br = new BufferedReader(ipsr);
			ligne = br.readLine();
			while (!ligne.equals(" ")) {
				lesQCMs.add(ligne);
				ligne = br.readLine();
				lesDifficultes.add(Integer.parseInt(ligne));
				ligne = br.readLine();
			}
		}
		catch (Exception e){
			System.out.println(e.toString());
		}
	}
	public QCM getLeQCM() {
		return leQCM;
	}
	public void setLeQCM(QCM leQCM) {
		this.leQCM = leQCM;
	}
	public ArrayList<String> getLesQCMs() {
		return lesQCMs;
	}
	public void setLesQCMs(ArrayList<String> lesQCMs) {
		this.lesQCMs = lesQCMs;
	}
	public ArrayList<Integer> getLesDifficultes() {
		return lesDifficultes;
	}
	public void setLesDifficultes(ArrayList<Integer> lesDifficultes) {
		this.lesDifficultes = lesDifficultes;
	}
	
	public void initialiser (String qcm)
	{/*
		QCM leQCM = new QCM (qcm);
		if (qcm.equals("DROIT1")) {
			int numQ = leQCM.addQuestion(new Question ("Le S.M.I.C. est :"));
			leQCM.getQuestion(numQ).addReponse("Le salaire minimum de croissance.");
			leQCM.getQuestion(numQ).addReponse("Le salaire minimum d'insertion communautaire.");
			leQCM.getQuestion(numQ).addReponse("Le salaire minimum interprofessionnel de croissance.");
			leQCM.getQuestion(numQ).addReponse("Le salaire minimum individuel connu.");
			leQCM.getQuestion(numQ).addReponse("Le salaire minimum invisiblement connu.");
			leQCM.getQuestion(numQ).setBonneReponse(1);
			numQ = leQCM.addQuestion(new Question ("En économie, l'O.M.C. désigne :"));
			leQCM.getQuestion(numQ).addReponse("L'Office Municipal de la Culture.");
			leQCM.getQuestion(numQ).addReponse("L'Organisation Mondiale du Commerce.");
			leQCM.getQuestion(numQ).addReponse("L'Organisme Mondialement Connu.");
			leQCM.getQuestion(numQ).addReponse("L'Oubli Méritoire des Connaissances. ");
			leQCM.getQuestion(numQ).addReponse("L'Outil Mécanique Critiqué.");
			leQCM.getQuestion(numQ).setBonneReponse(1);
			numQ = leQCM.addQuestion(new Question ("Dans une entreprise privée, les litiges entre salariés et employeur sont jugés en premier ressort par :"));
			leQCM.getQuestion(numQ).addReponse("Le tribunal de grande instance.");
			leQCM.getQuestion(numQ).addReponse("Le tribunal d'instance.");
			leQCM.getQuestion(numQ).addReponse("Le conseil d'administration.");
			leQCM.getQuestion(numQ).addReponse("Le Tribunal de Commerce.");
			leQCM.getQuestion(numQ).addReponse("Le Conseil des Prud'hommes.");
			leQCM.getQuestion(numQ).setBonneReponse(4);
		}
		else {
			int numQ = leQCM.addQuestion(new Question ("Quelle est la fonction permettant de créer un tableau ? "));
			leQCM.getQuestion(numQ).addReponse("Un crayon et une règle");
			leQCM.getQuestion(numQ).addReponse("La balise &ltTABLE> &lt/TABLE>");
			leQCM.getQuestion(numQ).addReponse("La balise &ltTABLEAU> &lt/TABLEAU>");
			leQCM.getQuestion(numQ).addReponse("La balise &ltTAB> &lt/TAB>");
			leQCM.getQuestion(numQ).setBonneReponse(1);
			numQ = leQCM.addQuestion(new Question ("Quelle fonction permet de définir une \"case\" dans un tableau ?"));
			leQCM.getQuestion(numQ).addReponse("Aucune");
			leQCM.getQuestion(numQ).addReponse("Insérer \"case\" dans l'assistant graphique");
			leQCM.getQuestion(numQ).addReponse("La balise &ltTR> &lt/TR>");
			leQCM.getQuestion(numQ).addReponse("La balise &ltTD> &lt/TD>");
			leQCM.getQuestion(numQ).setBonneReponse(3);
			numQ = leQCM.addQuestion(new Question ("Comment insère-t-on un titre dans un tableau ?"));
			leQCM.getQuestion(numQ).addReponse("Avec a balise &ltTITLE> &lt/TITLE>");
			leQCM.getQuestion(numQ).addReponse("On écrit le titre directement dans le tableau");
			leQCM.getQuestion(numQ).addReponse("Avec la balise &ltCAPTION> &lt/CAPTION>");
			leQCM.getQuestion(numQ).addReponse("Avec la balise &ltTH> &lt/TH>");
			leQCM.getQuestion(numQ).setBonneReponse(3);
		}*/
		String ligne2;
		int i, numQ;
		QCM leQCM = new QCM (qcm);
		ligne2 = "/QCMs/"
			+ qcm + ".txt";
		try{
			InputStream ips=new FileInputStream(ligne2); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			
			ligne2 = br.readLine();
			leQCM.setDifficulte(Integer.parseInt(ligne2)); // difficulte du qcm
			ligne2 = br.readLine();
			i = Integer.parseInt(ligne2); // nombre de questions a lire
			for (int j = 0; j < i; j++) {
				// lecture question
				ligne2 = br.readLine();
				numQ = leQCM.addQuestion(new Question(ligne2));
				// lecture numero bonne reponse
				ligne2 = br.readLine();
				leQCM.getQuestion(numQ).setBonneReponse(Integer.parseInt(ligne2));
				// lecture des reponses
				ligne2 = br.readLine();
				while (!(ligne2.equals(" "))) {
					leQCM.getQuestion(numQ).addReponse(ligne2);
					ligne2 = br.readLine();
				}
			}
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		this.leQCM = leQCM;
	}
}