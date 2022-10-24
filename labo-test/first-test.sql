#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Client
#------------------------------------------------------------

CREATE TABLE Client(
        id_client Int  Auto_increment  NOT NULL ,
        Nom       Varchar (50) NOT NULL ,
        Prenom    Varchar (50) NOT NULL
	,CONSTRAINT Client_PK PRIMARY KEY (id_client)
)ENGINE=InnoDB;

