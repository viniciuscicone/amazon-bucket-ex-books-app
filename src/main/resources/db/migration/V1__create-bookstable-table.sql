CREATE TABLE IF NOT EXISTS bookstable (
    id VARCHAR(37) PRIMARY KEY UNIQUE NOT NULL,
    name_book VARCHAR(200) NOT NULL,
    autor VARCHAR(200) NOT NULL,
    sinopse mediumtext,
    img_url VARCHAR(255),
    nota INT NOT NULL,
    CONSTRAINT chk_nota CHECK (nota BETWEEN 1 AND 5)
);