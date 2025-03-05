CREATE DATABASE management_job;
USE  management_job;

CREATE TABLE candidat(
    id_candidat int(10) PRIMARY KEY AUTO_INCREMENT,
    nom_candiat VARCHAR(50),
    email_candidat VARCHAR(200)
);

CREATE TABLE offre_emploi(
    id_offre int(10) PRIMARY KEY AUTO_INCREMENT,
    titre_offre VARCHAR(50),
    description VARCHAR(1000),
    date DATE
);

CREATE TABLE candidature(
   id_offre int,
   id_candidat int,
    PRIMARY KEY (id_offre, id_candidat),
    FOREIGN KEY (id_offre) REFERENCES offre_emploi(id_offre),
    FOREIGN KEY (id_candidat) REFERENCES candidat(id_candidat)
);

CREATE TABLE user(
    id_user int(10) PRIMARY KEY AUTO_INCREMENT,
    email_user VARCHAR(100),
    password VARCHAR(100),
    role VARCHAR(100)
)