DROP TABLE IF EXISTS USER;

CREATE TABLE USER (
  ID INT AUTO_INCREMENT PRIMARY KEY,
  USERNAME VARCHAR(200) NOT NULL,
  PASSWORD VARCHAR(200) NOT NULL,
  ACTIVE BOOLEAN,
  ROLES VARCHAR(200),
  UNIQUE (USERNAME)
);

INSERT INTO USER VALUES(1, 'anibal', 'pass', TRUE, 'USER');