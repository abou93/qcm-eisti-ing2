package Modele;

import java.util.ArrayList;

public class Resultat {
	private QCM qcmResultat;

	public Resultat(QCM qcm)
	{
		this.qcmResultat = qcm;
		for(int i = 0; i < this.qcmResultat.getNbQuestions(); i++)
		{
			for(int j = 0; j < this.qcmResultat.getQuestion(i).getNbReponses(); j++)
			{
				System.out.print(this.qcmResultat.getQuestion(i).getReponse(j).getExpression());
				System.out.print(this.qcmResultat.getQuestion(i).getReponse(j).isSelect());
				System.out.println(this.qcmResultat.getQuestion(i).getReponse(j).isTrue());
			}
		}
	}

	public QCM getQcmResultat() {
		return qcmResultat;
	}

	public void setQcmResultat(QCM qcmResultat) {
		this.qcmResultat = qcmResultat;
	}
	
	public void copierQCM(QCM qcm) {
		this.qcmResultat = qcm;
	}
	
	public void setReponse(int indexQ, int indexR, boolean v) {
		this.qcmResultat.getQuestion(indexQ).getReponse(indexR).setSelect(v);
	}

	public void initialiser(QCM qcm) {
		
	}
}
