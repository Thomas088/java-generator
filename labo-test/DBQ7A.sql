CREATE TABLE Cours(
   IdCours INT AUTO_INCREMENT,
   NomCours VARCHAR(50),
   PRIMARY KEY(IdCours)
);

CREATE TABLE Ville(
   IdVille INT AUTO_INCREMENT,
   NomVille VARCHAR(50),
   PRIMARY KEY(IdVille)
);

CREATE TABLE Etudiant(
   IdEtudiant INT AUTO_INCREMENT,
   NomEtudiant VARCHAR(50),
   PrenomEtudiant VARCHAR(50),
   IdVille INT NOT NULL,
   PRIMARY KEY(IdEtudiant),
   FOREIGN KEY(IdVille) REFERENCES Ville(IdVille)
);

CREATE TABLE R_EtudiantCours(
   IdEtudiant INT,
   IdCours INT,
   Cotisation BOOLEAN NOT NULL,
   PRIMARY KEY(IdEtudiant, IdCours),
   FOREIGN KEY(IdEtudiant) REFERENCES Etudiant(IdEtudiant),
   FOREIGN KEY(IdCours) REFERENCES Cours(IdCours)
);
