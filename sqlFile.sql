DROP DATABASE IF EXISTS bullsAndCowsDb;
CREATE DATABASE bullsAndCowsDb;

USE bullsAndCowsDb;

CREATE TABLE IF NOT EXISTS `game` (
	`gameID` int not null auto_increment,
    `gameStatus` varchar(30) not null,
    `computerAnswer`int not null,
    PRIMARY KEY (`gameID`)
);

CREATE TABLE IF NOT EXISTS `round` (
	`roundID` int not null auto_increment,
    `userAnswer` int not null,
	`result` varchar(30) not null,
    `time` DATETIME not null,
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

select * from game;
select * from round;
select * from gameRound;

SELECT round.roundID, userAnswer, result, time FROM round 
INNER JOIN gameRound gr ON round.roundID = gr.roundID 
INNER JOIN game g ON gr.gameID = g.gameID
WHERE g.gameID = 1
GROUP BY round.roundID
ORDER BY time asc;