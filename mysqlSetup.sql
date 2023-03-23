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