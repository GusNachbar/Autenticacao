DROP TABLE "user";

CREATE TABLE "user" (
    IdUser INT NOT NULL,
    UserName VARCHAR(255) NOT NULL,
    Password VARCHAR(255) NOT NULL
);

INSERT INTO "user" VALUES (1, 'gustavo.nachbar@iteris.com.br', '$2a$10$WY6SgugzKxZecq67Wow.eOCWU/NYj.n0GkWmQOArSjD2tVTerYyu6');