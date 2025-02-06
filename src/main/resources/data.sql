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
INSERT INTO public.activities(weekend_id, title, description, location, costs, added_by)
VALUES (1, 'Paardrijden', 'Lekker door de duinen rijden', 'Loonse en Drunense duinen', '65', 3),
       (1, 'Suppen', 'Staand peddelen op een board op het water', 'Scheveningen', '40', 4),
       (1, 'Bioscoop', 'Naar de nieuwste film', 'VUE Den Bosch', '20', 5),
       (1, 'Karten', 'Lekker hard scheuren', 'Uden', '35', 6),
       (1, 'Fietsen', 'De omgeving verkennen te fiets', 'Maastricht', '45', 3),
       (1, 'Beeldhouwen', 'Is een cursus om een mooi beeld te maken', 'Ravenstein', '120', 4),
       (1, 'Paintballen', 'Op elkaar schieten met verfballetjes', 'Utrecht', '30', 5),
       (1, 'Schilderen', 'Cursus bloemen schilderen', 'Deventer', '110', 4),
       (1, 'Spelletjes spelen', 'Gewoon lekker ouderwetse spelletjes spelen', 'thuis', '0', 4);

--groups
INSERT INTO public.groups (weekend_id)
VALUES (1);

--group_users
INSERT INTO group_users (group_id, user_id)
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

--images
INSERT INTO public.images (file_name)
VALUES ('1_paardrijden.jpg'),
       ('2_suppen.jpg'),
       ('3_bios.jpg'),
       ('4_karten.jpg'),
       ('5_fietsen.jpg'),
       ('6_beeldhouwen.jpg'),
       ('7_paintballen.jpg'),
       ('8_schilderen.jpg'),
       ('9_spelletje.jpg');


UPDATE public.activities
SET image_file_name = '1_paardrijden.jpg'
WHERE id = 1;

UPDATE public.activities
SET image_file_name = '2_suppen.jpg'
WHERE id = 2;

UPDATE public.activities
SET image_file_name = '3_bios.jpg'
WHERE id = 3;

UPDATE public.activities
SET image_file_name = '4_karten.jpg'
WHERE id = 4;

UPDATE public.activities
SET image_file_name = '5_fietsen.jpg'
WHERE id = 5;

UPDATE public.activities
SET image_file_name = '6_beeldhouwen.jpg'
WHERE id = 6;

UPDATE public.activities
SET image_file_name = '7_paintballen.jpg'
WHERE id = 7;

UPDATE public.activities
SET image_file_name = '8_schilderen.jpg'
WHERE id = 8;

UPDATE public.activities
SET image_file_name = '9_spelletje.jpg'
WHERE id = 9;
