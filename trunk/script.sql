DROP TABLE Utilisateur CASCADE CONSTRAINT;

CREATE TABLE Utilisateur (
	user_id NUMBER CONSTRAINT pk_utilisateur PRIMARY KEY,
	user_login VARCHAR2(50),
	user_password VARCHAR2(50),
	user_est_prof NUMBER);
	
INSERT INTO Utilisateur VALUES (1, 'rachou', '123', 0);
INSERT INTO Utilisateur VALUES (2, 'stitch', '456', 1);
INSERT INTO Utilisateur VALUES (3, 'michou', '789', 1);
INSERT INTO Utilisateur VALUES (4, 'fransou', '000', 0);



/*-------------------------------------------------*/

DROP TABLE Cours CASCADE CONSTRAINT;
DROP TABLE Qcm CASCADE CONSTRAINT;

CREATE TABLE Qcm (
	id NUMBER(4) CONSTRAINT pk_qcm PRIMARY KEY,
	qcmXML XMLTYPE,
  id_cours NUMBER(4)
	);
	
CREATE TABLE Cours (
	id NUMBER(4) CONSTRAINT pk_cours PRIMARY KEY,
	nom VARCHAR2(50)
	);	
	
ALTER TABLE Qcm ADD CONSTRAINT fk_qcm_cours FOREIGN KEY (id_cours) REFERENCES Cours (id);  


/** Insertion Cours **/
INSERT INTO Cours (ID, NOM) VALUES ('1', 'Droit');
INSERT INTO Cours (ID, NOM) VALUES ('2', 'HTML');