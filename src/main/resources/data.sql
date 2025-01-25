INSERT INTO public.activities(title, description, added_by, votes)
VALUES ('Paardrijden', 'Lekker door het bos rijden', 'Nils', 0),
       ('Suppen', 'Iets in het water doen', 'Eva', 0),
       ('Bioscoop', 'Naar de nieuwste film', 'Zoe', 0),
       ('Karten', 'Lekker hard scheuren', 'Yasmina', 0),
       ('Fietsen', 'Lekker door de omgeving fietsen', 'Nils', 0),
       ('Borduren', 'Creatief bezig zijn', 'Eva', 0),
       ('Kleien', 'Met onze handen werken', 'Zoe', 0),
       ('Schilderen', 'Iets moois schilderen', 'Nils', 0),
       ('Spelletjes spelen', 'Gezellig monopoly spelen', 'Eva', 0)
--        ('Massage', 'Lekker ontspannen', 'Zoe', 0),
--        ('Wandelen', 'Lekker in het bos wandelen', 'Nils', 0),
--        ('Slapen', 'Ik heb nergens anders zin in', 'Eva', 0),
--        ('Theatervoorstelling', 'Naar de nieuwste voorstelling', 'Zoe', 0)

;
INSERT INTO users (username, password, role, email)
VALUES ('admin', '$2a$10$8T3ol9KA/UD7kIA5g0tYwuSy3H9G8BhviFQnPa29Kqx9LHq4tqNwS', 'ADMIN', 'admin@hotmail.com');
INSERT INTO users (username, password, role, email)
VALUES ('user', '$2a$10$6DLf/0/itBa.MEKadeb0Me0rZA.8Fm2t327DNyMv8CdfJ8CH0jtRG', 'USER', 'user@hotmail.com');
INSERT INTO users (username, password, role, email)
VALUES ('Nils', '$2a$10$IqztpTZkAEFEefgobIwfLuVxAhTleawXIAbdOnE06UJTDz.W5NO1S', 'USER', 'nils@hotmail.com');
INSERT INTO users (username, password, role, email)
VALUES ('Eva', '$2a$10$vIpVyfC.V5EvmNYF6DYWH.riKzljL9KDLFFE2LjHFzPlu.HTyjfHy', 'USER', 'eva@hotmail.com');
INSERT INTO users (username, password, role, email)
VALUES ('Zoe', '$2a$10$AxuPCG9YPSpfJfzo9mV.DOyMnqLTvlAjYvw.nbJQk5fqyfbQteFvq', 'USER', 'zoe@hotmail.com');
INSERT INTO users (username, password, role, email)
VALUES ('Yasmina', '$2a$10$d3u1KzyI93enqnsfRdZ3iuIZvbyvEeP94aIHhNAMSyisGt3sCgAUu', 'USER', 'yasminakuppen@hotmail.com');

INSERT INTO public.weekends(date, time, location, temperature)
VALUES ('10-1-2025', '16.00', 'Den Bosch', '30');

INSERT INTO public.groups (user_id, type)
VALUES ('3', 'Familieweekend');
VALUES ( '4', 'Familieweekend');
VALUES ( '5', 'Familieweekend');
VALUES ( '6', 'Familieweekend');