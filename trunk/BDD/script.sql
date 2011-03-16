DROP TABLE Utilisateur CASCADE CONSTRAINT;
DROP TABLE Cours CASCADE CONSTRAINT;
DROP TABLE Qcm CASCADE CONSTRAINT;


CREATE TABLE Utilisateur (
	user_id NUMBER CONSTRAINT pk_utilisateur PRIMARY KEY,
	user_login VARCHAR2(50),
	user_password VARCHAR2(50),
	user_est_prof NUMBER);
	

CREATE TABLE Qcm (
	id NUMBER(4) CONSTRAINT pk_qcm PRIMARY KEY,
	xml XMLTYPE,
	id_cours NUMBER(4),
	id_user NUMBER
	);
	
CREATE TABLE Cours (
	id NUMBER(4) CONSTRAINT pk_cours PRIMARY KEY,
	nom VARCHAR2(50)
	);	
	
ALTER TABLE Qcm ADD CONSTRAINT fk_qcm_cours FOREIGN KEY (id_cours) REFERENCES Cours (id); 
ALTER TABLE Qcm ADD CONSTRAINT fk_qcm_user FOREIGN KEY (id_user) REFERENCES Utilisateur (user_id);  


/** Insertion **/
INSERT INTO Utilisateur VALUES (0, 'cours', null, null);
INSERT INTO Utilisateur VALUES (1, 'rachou', '123', 0);
INSERT INTO Utilisateur VALUES (2, 'stitch', '456', 1);
INSERT INTO Utilisateur VALUES (3, 'michou', '789', 1);
INSERT INTO Utilisateur VALUES (4, 'fransou', '000', 0);
INSERT INTO Cours (ID, NOM) VALUES ('1', 'Droit');
INSERT INTO Cours (ID, NOM) VALUES ('2', 'HTML');

Insert into QCM values (1, XMLType(
'<qcm temps="240" lvl="1" date="01/01/2011">
	<question>
		<expression>Le S.M.I.C. est :</expression>
		<reponse value="false">Le salaire minimum de croissance.</reponse>
		<reponse value="false">Le salaire minimum d''insertion communautaire.</reponse>
		<reponse value="true">Le salaire minimum interprofessionnel de croissance.</reponse>
		<reponse value="false">Le salaire minimum individuel connu.</reponse>
		<reponse value="false">Le salaire minimum invisiblement connu.</reponse>
	</question>
	<question>
		<expression>En economie, l''O.M.C. designe :</expression>
		<reponse value="false">L''Office Municipal de la Culture.</reponse>
		<reponse value="true">L''Organisation Mondiale du Commerce.</reponse>
		<reponse value="false">L''Organisme Mondialement Connu.</reponse>
		<reponse value="false">L''Oubli Meritoire des Connaissances.</reponse>
		<reponse value="false">L''Outil Mecanique Critique.</reponse>
	</question>
	<question>
		<expression>Dans une entreprise privee, les litiges entre salaries et employeur sont juges en premier ressort par :</expression>
		<reponse value="false">Le tribunal de grande instance.</reponse>
		<reponse value="false">Le tribunal d''instance.</reponse>
		<reponse value="false">Le conseil d''administration.</reponse>
		<reponse value="true">Le Tribunal de Commerce.</reponse>
		<reponse value="false">Le Conseil des Prud''hommes.</reponse>
	</question>
</qcm>'),1,0);
Insert into QCM values (2,XMLType(
'<qcm temps="180" lvl="4" date="01/02/2011">
	<question>
		<expression>Dans le domaine syndical : </expression>
		<reponse value="true">Chacun est libre de constituer un syndicat.</reponse>
		<reponse value="false">L''adhesion a un syndicat necessite l''accord de l''employeur.</reponse>
		<reponse value="false">L''adhesion a un syndicat est obligatoire.</reponse>
		<reponse value="false">Mes activites syndicales peuvent etre une raison legale pour m''ecarter d''un emploi a pourvoir.</reponse>
		<reponse value="false">Les employeurs n''ont pas d''organisation syndicale.</reponse>
	</question>
	<question>
		<expression>Le metier de l''entreprise c''est :</expression>
		<reponse value="true">Le nombre d''annees depuis lequel elle exerce son activite.</reponse>
		<reponse value="false">Le savoir-faire de l''entreprise.</reponse>
		<reponse value="false">Vendre des produits.</reponse>
		<reponse value="false">Recruter des salaries.</reponse>
		<reponse value="false">Organiser des fetes de temps en temps.</reponse>
	</question>
	<question>
		<expression>La greve licite a pour consequence :</expression>
		<reponse value="true">La suspension du contrat de travail.</reponse>
		<reponse value="true">La rupture du contrat de travail.</reponse>
		<reponse value="false">Le paiement des jours de greve.</reponse>
		<reponse value="false">Le remplacement des salaries grevistes.</reponse>
		<reponse value="false">Des jours de conge supplementaires.</reponse>
	</question>
</qcm>'),1,0);

Insert into QCM values (3, XMLType(
'<qcm temps="120" lvl="2" date="01/04/2011">
	<question>
		<expression>Quelle est la fonction permettant de creer un tableau ?</expression>
		<reponse value="false">Un crayon et une regle</reponse>
		<reponse value="true">La balise <TABLE> </TABLE></reponse>
		<reponse value="false">La balise <TABLEAU> </TABLEAU></reponse>
		<reponse value="false">La balise <TAB> </TAB></reponse>
	</question>
	<question>
		<expression>Quelle fonction permet de definir une "case" dans un tableau ?</expression>
		<reponse value="false">Aucune</reponse>
		<reponse value="false">Inserer "case" dans l''assistant graphique</reponse>
		<reponse value="true">La balise <TR> </TR></reponse>
		<reponse value="true">La balise <TD> </TD></reponse>
	</question>
	<question>
		<expression>Comment insee-t-on un titre dans un tableau ?</expression>
		<reponse value="false">Avec a balise <TITLE> </TITLE></reponse>
		<reponse value="false">On ecrit le titre directement dans le tableau</reponse>
		<reponse value="false">Avec la balise <CAPTION> </CAPTION></reponse>
		<reponse value="false">Avec la balise <TH> </TH></reponse>
	</question>
</qcm>'),2,0);


commit;