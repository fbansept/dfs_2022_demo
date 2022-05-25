INSERT INTO role (id, nom) VALUES
(1,'ROLE_USER'),
(2,'ROLE_REDACTEUR'),
(3,'ROLE_ADMIN');

INSERT INTO utilisateur (id, prenom, nom,email, numero_token, mot_de_passe) VALUES
(1, 'franky', 'bansept', 'toto@toto.com', 1 ,'$2a$10$AUz7WdRb8HukUyhZ4sFfHOFCY5ik2plVgyswIGLNdMicqKQOEhExO'),
(2, 'john', 'doe', 'tata@tata.com', 1 , '$2a$10$AUz7WdRb8HukUyhZ4sFfHOFCY5ik2plVgyswIGLNdMicqKQOEhExO'),
(3, 'steeve', 'smith', 'titi@titi.com', 1 ,'$2a$10$AUz7WdRb8HukUyhZ4sFfHOFCY5ik2plVgyswIGLNdMicqKQOEhExO');

INSERT INTO role_utilisateur (utilisateur_id, role_id) VALUES
(1,1),
(1,3),
(2,1),
(2,2),
(3,1);

INSERT INTO marque (id, nom) VALUES
(1, 'DELL'),
(2, 'BIC');

INSERT INTO materiel (id, nom, code, marque_id) VALUES
(1, 'Ecran', 'ECRANDELL001', 1),
(2, 'Clavier', 'CLAVIERDELL001', 1),
(3, 'Marqueur rouge', 'MARQUEURROUGE', 2);

INSERT INTO specificite (id, nom) VALUES
(1, 'A cadenasser'),
(2, 'Fragile');

INSERT INTO materiel_specificite (materiel_id, specificite_id) VALUES
(1, 1),
(1, 2),
(2, 2);

INSERT INTO reservation (date,materiel_id, emprunteur_id) VALUES
('2022-02-01', 1 , 2),
('2022-02-02', 2 , 2);
