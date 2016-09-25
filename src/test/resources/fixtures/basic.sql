INSERT INTO CINEMA(CITY, NAME) VALUES ('Lublin', 'Multikino');
INSERT INTO CINEMA(CITY, NAME) VALUES ('Lublin', 'Cinema City');
INSERT INTO CINEMA(CITY, NAME) VALUES ('Warszawa', 'Arkadia');

INSERT INTO MOVIE(DESCRIPTION, LENGTH, MINAGE, TITLE) VALUES ('Good movie', 123, 18, 'Pulp Fiction');
INSERT INTO MOVIE(DESCRIPTION, LENGTH, MINAGE, TITLE)  VALUES ('Good movie', 100, 16, 'Green Mile');
INSERT INTO MOVIE(DESCRIPTION, LENGTH, MINAGE, TITLE)  VALUES ('Good movie', 95, 12, 'Desperados');

INSERT INTO ACTOR(NAME) VALUES ('John Travolta');
INSERT INTO ACTOR(NAME) VALUES ('Tom Hanks');
INSERT INTO ACTOR(NAME) VALUES ('Antonio Banderas');

INSERT INTO GENRE(NAME) VALUES ('Drama');
INSERT INTO GENRE(NAME) VALUES ( 'Comedy');
INSERT INTO GENRE(NAME) VALUES ( 'Fiction');

INSERT INTO MOVIE_ACTOR VALUES (1, 1);
INSERT INTO MOVIE_ACTOR VALUES (2, 2);
INSERT INTO MOVIE_ACTOR VALUES (3, 3);

INSERT INTO MOVIE_GENRE VALUES (1, 3);
INSERT INTO MOVIE_GENRE VALUES (2, 1);
INSERT INTO MOVIE_GENRE VALUES (3, 2);

INSERT INTO SHOW VALUES ('2016-10-10', '20:00', 1, 1);
INSERT INTO SHOW VALUES ('2016-10-12', '19:00', 2, 2);
INSERT INTO SHOW VALUES ('2016-10-14', '16:00', 3, 3);

INSERT INTO TICKETPRICE(PRICE, TYPE) VALUES ( 20, 'Regular');
INSERT INTO TICKETPRICE(PRICE, TYPE) VALUES ( 17, 'Student');
INSERT INTO TICKETPRICE(PRICE, TYPE) VALUES ( 12, 'School');




