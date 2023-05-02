CREATE TABLE `player` (
  `playerID` varchar(20) NOT NULL,
  `player_name` varchar(20) NOT NULL,
  `player_birth_date` date NOT NULL,
  `position` varchar(20) NOT NULL,
  `player_draft_year` year(4) NOT NULL,
  PRIMARY KEY (`playerID`)
)
INSERT INTO player VALUES
    ('PL01', 'Dwight Howard', '1985-12-08', 'C', '2004 '),
    ('PL02', 'LeBron James', '1984-12-30', 'SF', '2003 '),
    ('PL03', 'Stephen Curry', '1988-03-14', 'PG', '2009 '),
    ('PL04', 'Rudy Gobert', '1992-06-26', 'C', '2013 '),
	('PL05','Kevin Love','1988-09-07','PF','2008');



CREATE TABLE `scout` (
  `scoutID` varchar(20) NOT NULL DEFAULT '0',
  `scout_name` varchar(20) NOT NULL,
  `scout_birth_date` date NOT NULL,
  PRIMARY KEY (`scoutID`)
)

INSERT INTO scout(scout_name,scout_birth_date) VALUES
    ('Anthony Gacona', '1977-08-07'),
    ('Andrew Mealae', '1972-03-04'),
    ('Irving Thomas JR.', '1964-03-09'),
    ('Craig Johnson', '1974-03-11'),
	('Elan Vinokurov','1962-12-10'),
	('John Carideo', '1964-01-18'),
    ('David Sevush', '1963-12-26'),
    ('Will Chapman', '1970-05-17'),
    ('Keith Askins', '1971-02-17'),
    ('Kevin Tiller', '1963-03-25'),
	('Zarko Durisic','1964-02-21');

CREATE TABLE scout_seq ( scoutID INT NOT NULL AUTO_INCREMENT PRIMARY KEY );

DELIMITER $$
CREATE TRIGGER tg_scout_insert
BEFORE INSERT ON scout
FOR EACH ROW
BEGIN
  INSERT INTO scout_seq VALUES (NULL);
  SET NEW.scoutID = CONCAT('SC', LPAD(LAST_INSERT_ID(), 2, '0'));
END$$
DELIMITER ;

CREATE TABLE `team` (
  `teamID` varchar(20) NOT NULL,
  `team_name` varchar(50) NOT NULL,
  `team_salary` int(30) NOT NULL,
  PRIMARY KEY (`teamID`)
)

