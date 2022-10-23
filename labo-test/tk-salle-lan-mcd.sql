#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: client
#------------------------------------------------------------

CREATE TABLE client(
        idClient  Int  Auto_increment  NOT NULL ,
        firstName Varchar (50) NOT NULL ,
        lastName  Varchar (50) NOT NULL ,
        birthdate Date NOT NULL ,
        gender    Char (1) NOT NULL
	,CONSTRAINT client_PK PRIMARY KEY (idClient)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: computer
#------------------------------------------------------------

CREATE TABLE computer(
        idComputer Int  Auto_increment  NOT NULL ,
        pcName     Varchar (50) NOT NULL ,
        hostname   Varchar (50) NOT NULL ,
        ipAddress  Varchar (50) NOT NULL
	,CONSTRAINT computer_PK PRIMARY KEY (idComputer)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: game
#------------------------------------------------------------

CREATE TABLE game(
        idGame    Int  Auto_increment  NOT NULL ,
        gameName  Varchar (50) NOT NULL ,
        gametype  Varchar (50) NOT NULL ,
        publisher Varchar (50) NOT NULL
	,CONSTRAINT game_PK PRIMARY KEY (idGame)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: party
#------------------------------------------------------------

CREATE TABLE party(
        idParty         Int  Auto_increment  NOT NULL ,
        availablePlaces Int NOT NULL ,
        ping            Int NOT NULL ,
        partyStart      Datetime NOT NULL ,
        partyEnd        Datetime NOT NULL ,
        gain            Int NOT NULL ,
        gainUnit        Varchar (50) NOT NULL ,
        price           Float NOT NULL ,
        idGame          Int
	,CONSTRAINT party_PK PRIMARY KEY (idParty)

	,CONSTRAINT party_game_FK FOREIGN KEY (idGame) REFERENCES game(idGame)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: credit_card
#------------------------------------------------------------

CREATE TABLE credit_card(
        idCreditCard Int  Auto_increment  NOT NULL ,
        cardName     Varchar (50) NOT NULL ,
        cardNumber   Varchar (50) NOT NULL ,
        bank         Varchar (100) NOT NULL ,
        IBAN         Varchar (100) NOT NULL
	,CONSTRAINT credit_card_PK PRIMARY KEY (idCreditCard)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: player
#------------------------------------------------------------

CREATE TABLE player(
        idPlayer    Int  Auto_increment  NOT NULL ,
        gamertag    Varchar (50) NOT NULL ,
        playerLevel Int NOT NULL ,
        pointUnit   Varchar (20) NOT NULL ,
        idClient    Int NOT NULL
	,CONSTRAINT player_PK PRIMARY KEY (idPlayer)

	,CONSTRAINT player_client_FK FOREIGN KEY (idClient) REFERENCES client(idClient)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: employee
#------------------------------------------------------------

CREATE TABLE employee(
        idEmployee Int  Auto_increment  NOT NULL ,
        firstName  Varchar (50) NOT NULL ,
        lastName   Varchar (50) NOT NULL ,
        birthdate  Date NOT NULL ,
        gender     Char (5) NOT NULL
	,CONSTRAINT employee_PK PRIMARY KEY (idEmployee)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: payment
#------------------------------------------------------------

CREATE TABLE payment(
        idPayment         Int  Auto_increment  NOT NULL ,
        amount            Float NOT NULL ,
        VAT               Float NOT NULL ,
        currency          Varchar (50) NOT NULL ,
        isPaymentAccepted Bool NOT NULL ,
        idClient          Int ,
        idCreditCard      Int ,
        idEmployee        Int ,
        idParty           Int NOT NULL
	,CONSTRAINT payment_PK PRIMARY KEY (idPayment)

	,CONSTRAINT payment_client_FK FOREIGN KEY (idClient) REFERENCES client(idClient)
	,CONSTRAINT payment_credit_card0_FK FOREIGN KEY (idCreditCard) REFERENCES credit_card(idCreditCard)
	,CONSTRAINT payment_employee1_FK FOREIGN KEY (idEmployee) REFERENCES employee(idEmployee)
	,CONSTRAINT payment_party2_FK FOREIGN KEY (idParty) REFERENCES party(idParty)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: playmember
#------------------------------------------------------------

CREATE TABLE playmember(
        idPlaymember Int  Auto_increment  NOT NULL ,
        idComputer   Int NOT NULL ,
        idPlayer     Int NOT NULL
	,CONSTRAINT playmember_PK PRIMARY KEY (idPlaymember)

	,CONSTRAINT playmember_computer_FK FOREIGN KEY (idComputer) REFERENCES computer(idComputer)
	,CONSTRAINT playmember_player0_FK FOREIGN KEY (idPlayer) REFERENCES player(idPlayer)
)ENGINE=InnoDB COMMENT "Les participants a la LAN" ;


#------------------------------------------------------------
# Table: card_client
#------------------------------------------------------------

CREATE TABLE card_client(
        idCreditCard Int NOT NULL ,
        idClient     Int NOT NULL
	,CONSTRAINT card_client_PK PRIMARY KEY (idCreditCard,idClient)

	,CONSTRAINT card_client_credit_card_FK FOREIGN KEY (idCreditCard) REFERENCES credit_card(idCreditCard)
	,CONSTRAINT card_client_client0_FK FOREIGN KEY (idClient) REFERENCES client(idClient)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: party_player
#------------------------------------------------------------

CREATE TABLE party_player(
        idPlaymember Int NOT NULL ,
        idParty      Int NOT NULL
	,CONSTRAINT party_player_PK PRIMARY KEY (idPlaymember,idParty)

	,CONSTRAINT party_player_playmember_FK FOREIGN KEY (idPlaymember) REFERENCES playmember(idPlaymember)
	,CONSTRAINT party_player_party0_FK FOREIGN KEY (idParty) REFERENCES party(idParty)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: party_computer
#------------------------------------------------------------

CREATE TABLE party_computer(
        idComputer Int NOT NULL ,
        idParty    Int NOT NULL
	,CONSTRAINT party_computer_PK PRIMARY KEY (idComputer,idParty)

	,CONSTRAINT party_computer_computer_FK FOREIGN KEY (idComputer) REFERENCES computer(idComputer)
	,CONSTRAINT party_computer_party0_FK FOREIGN KEY (idParty) REFERENCES party(idParty)
)ENGINE=InnoDB;

