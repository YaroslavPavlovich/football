INSERT INTO fc_tournament (tournament_name, country)
VALUES
('Ball','Russia'),
('Foot','UK');

INSERT INTO fc_team ("command_name",players)
VALUES
('First',43),
('Second',50);

INSERT INTO fc_tournament_team (id_tournament,id_team)
VALUES
(1,1),
(1,2),
(2,1);

INSERT INTO fc_match (id_owners, id_guests, score_owners, score_guests,id_tournament,match_date)
VALUES
(1,2,5,0,1,'1999-01-08'),
(2,1,0,5,1,'1999-01-09'),
(2,1,2,3,2,'2020-01-01');

INSERT INTO fc_coach (id, id_team, surname_coach, name_coach, age,experiance)
VALUES
(1,1,'FirstCoach','Name',55,20),
(2,2,'SecondCoach','NameMore',64,30);

INSERT INTO fc_news (id, name_news,content_news)
VALUES
(1,'FirstNews', 'GoalGOALgoal'),
(2,'SecondNews', 'MissedMISS');

INSERT INTO fc_team_news (id_news, id_team)
VALUES
(1,2),
(1,1),
(2,1);

INSERT INTO fc_player (surname_player, name_player, height, weight, age)
VALUES
('Schegolihin', 'Yaroslav', 184, 80, 21),
('Robov', 'Vitaliy', 190, 85, 25),
('Vovov', 'Grey', 176, 67, 20),
('Иванов', 'Иван', 176, 67, 20);

INSERT INTO fc_player_team (id_player, id_team)
VALUES
(1,1),
(1,2),
(2,1),
(4,2);

INSERT INTO fc_event (name_event,content_event)
VALUES
('FirstEvent', 'GoalGOALgoal'),
('SecondEvent', 'MissedMISS');

INSERT INTO fc_player_event (id_event, id_player)
VALUES
(1,2),
(2,2),
(1,3);

INSERT INTO fc_tournament_result (id_team, id_tournament,place,points,wins,
                             loses,draw,goals_missed,goals,missed)
VALUES
(1,1,1,9,3,0,0,15,18,3),
(2,1,2,4,1,0,0,-15,3,18),
(2,2,2,4,1,0,0,-15,3,18);