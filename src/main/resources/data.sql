
--users
INSERT INTO users (username, password, role, email)
 VALUES ('admin', '$2a$10$8T3ol9KA/UD7kIA5g0tYwuSy3H9G8BhviFQnPa29Kqx9LHq4tqNwS', 'ADMIN', 'admin@hotmail.com'),
        ('user', '$2a$10$6DLf/0/itBa.MEKadeb0Me0rZA.8Fm2t327DNyMv8CdfJ8CH0jtRG', 'USER', 'user@hotmail.com'),
        ('Nils', '$2a$10$IqztpTZkAEFEefgobIwfLuVxAhTleawXIAbdOnE06UJTDz.W5NO1S', 'USER', 'nils@hotmail.com'),
        ('Eva', '$2a$10$vIpVyfC.V5EvmNYF6DYWH.riKzljL9KDLFFE2LjHFzPlu.HTyjfHy', 'USER', 'eva@hotmail.com'),
        ('Zoe', '$2a$10$AxuPCG9YPSpfJfzo9mV.DOyMnqLTvlAjYvw.nbJQk5fqyfbQteFvq', 'USER', 'zoe@hotmail.com'),
        ('Yasmina', '$2a$10$d3u1KzyI93enqnsfRdZ3iuIZvbyvEeP94aIHhNAMSyisGt3sCgAUu', 'USER', 'yasminakuppen@hotmail.com');

--weekends
INSERT INTO public.weekends(name, date, time, location, temperature, added_by)
VALUES ('Vriendinnen weekend', '10-1-2025', '16.00', 'Den Bosch', '30', 6);

--activities
INSERT INTO public.activities(weekend_id, title, description, added_by)
VALUES (1,'Paardrijden', 'Lekker door het bos rijden', 3),
       (1,'Suppen', 'Iets in het water doen', 4),
       (1,'Bioscoop', 'Naar de nieuwste film', 5),
       (1,'Karten', 'Lekker hard scheuren', 6),
       (1,'Fietsen', 'Lekker door de omgeving fietsen', 3),
       (1,'Borduren', 'Creatief bezig zijn', 4),
       (1,'Kleien', 'Met onze handen werken', 5),
       (1,'Schilderen', 'Iets moois schilderen', 4),
       (1,'Spelletjes spelen', 'Gezellig monopoly spelen', 4);

--groups
INSERT INTO public.groups (weekend_id, user_id)
VALUES (1, 1),
        (1, 2),
        (1, 3),
        (1, 4);

--votes
INSERT INTO public.votes (activity_id, user_id, votes)
VALUES (1, 5, 1),
       (3, 3, 1),
       (2, 2, 1),
       (1, 4, 1);