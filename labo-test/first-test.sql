#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: drone
#------------------------------------------------------------

CREATE TABLE drone(
        idDrone   Int  Auto_increment  NOT NULL ,
        brand     Varchar (100) NOT NULL ,
        model     Varchar (100) NOT NULL ,
        refDrone  Varchar (100) NOT NULL ,
        state     Int NOT NULL ,
        speedMax  Int NOT NULL ,
        speedUnit Varchar (10) NOT NULL ,
        isDeleted Bool NOT NULL
	,CONSTRAINT drone_PK PRIMARY KEY (idDrone)
)ENGINE=InnoDB;