CREATE TABLE Utilisateur (
	user_id NUMBER CONSTRAINT pk_utilisateur PRIMARY KEY,
	user_login VARCHAR2(50),
	user_password VARCHAR2(50),
	user_est_prof NUMBER);
	
INSERT INTO Utilisateur VALUES (1, 'rachou', '123', 0);
INSERT INTO Utilisateur VALUES (2, 'stitch', '456', 1);
INSERT INTO Utilisateur VALUES (3, 'michou', '789', 1);
INSERT INTO Utilisateur VALUES (4, 'fransou', '000', 0);