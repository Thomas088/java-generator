CREATE TABLE Ville(
   IdVille INT AUTO_INCREMENT,
   NomVille VARCHAR(50) NOT NULL,
   PRIMARY KEY(IdVille)
);

CREATE TABLE CodePostal(
   IdCodePostal INT AUTO_INCREMENT,
   CodePostal SMALLINT,
   IdVille INT NOT NULL,
   PRIMARY KEY(IdCodePostal),
   FOREIGN KEY(IdVille) REFERENCES Ville(IdVille)
);

CREATE TABLE Adresse(
   IdAdresse INT AUTO_INCREMENT,
   NomRue VARCHAR(50) NOT NULL,
   Numero SMALLINT NOT NULL,
   IdCodePostal INT NOT NULL,
   PRIMARY KEY(IdAdresse),
   FOREIGN KEY(IdCodePostal) REFERENCES CodePostal(IdCodePostal)
);

CREATE TABLE Personne(
   IdPersonne INT AUTO_INCREMENT,
   NomPersonne VARCHAR(50) NOT NULL,
   PrenomPersonne VARCHAR(50) NOT NULL,
   IdAdresse INT NOT NULL,
   PRIMARY KEY(IdPersonne),
   FOREIGN KEY(IdAdresse) REFERENCES Adresse(IdAdresse)
);

CREATE TABLE Groupe(
   IdGroupe INT AUTO_INCREMENT,
   NomGroupe VARCHAR(50) NOT NULL,
   PRIMARY KEY(IdGroupe)
);

CREATE TABLE R_PersonneGroupe(
   IdPersonne INT,
   IdGroupe INT,
   PRIMARY KEY(IdPersonne, IdGroupe),
   FOREIGN KEY(IdPersonne) REFERENCES Personne(IdPersonne),
   FOREIGN KEY(IdGroupe) REFERENCES Groupe(IdGroupe)
);
