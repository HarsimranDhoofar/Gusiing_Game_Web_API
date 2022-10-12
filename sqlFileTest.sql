DROP DATABASE IF EXISTS bullsAndCowsDbTest;
CREATE DATABASE bullsAndCowsDbTest;

USE bullsAndCowsDbTest;

CREATE TABLE IF NOT EXISTS `game` (
	`gameID` int not null auto_increment,
    `gameStatus` varchar(30) not null,
    `computerAnswer`int ,
    PRIMARY KEY (`gameID`)
);

CREATE TABLE IF NOT EXISTS `round` (
	`roundID` int not null auto_increment,
    `userAnswer` int,
	`result` varchar(30),
    `time` DATETIME,
    PRIMARY KEY (`roundID`)
);

CREATE TABLE IF NOT EXISTS `gameRound` (
	`gameRoundID` int not null auto_increment,
    `gameID` int not null,
    `roundID` int not null,
    PRIMARY KEY (`gameRoundID`)
);

ALTER TABLE `gameRound`
 ADD CONSTRAINT `fk_gameRoundgameID` FOREIGN KEY (`gameID`) REFERENCES `game`
(`gameID`) ON DELETE NO ACTION;

ALTER TABLE `gameRound`
 ADD CONSTRAINT `fk_gameRoundroundID` FOREIGN KEY (`roundID`) REFERENCES `round`
(`roundID`) ON DELETE NO ACTION;