INSERT INTO public.activities(
    title, description, added_by, votes)
VALUES
    ('Paardrijden', 'Lekker door het bos rijden', 'Nils', 0),
    ('Suppen', 'Iets in het water doen', 'Eva', 0)
;
INSERT INTO users (username, password, role) VALUES ('admin', '$2a$10$eSBsTk2H31K9S1mf4P3FeOTVL6yajf5ESOsXatA0cYKt.x5R9ZPIq', 'ADMIN');
INSERT INTO users (username, password, role) VALUES ('user', '$2a$10$6DLf/0/itBa.MEKadeb0Me0rZA.8Fm2t327DNyMv8CdfJ8CH0jtRG', 'USER');