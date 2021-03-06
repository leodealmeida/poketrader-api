CREATE TABLE trade (
   id BIGINT PRIMARY KEY NOT NULL,
   created_at TIMESTAMP NOT NULL DEFAULT NOW(),
   status BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE traded_pokemon (
   id BIGINT  PRIMARY KEY NOT NULL,
   pokemon_name TEXT NOT NULL,
   side CHAR NOT NULL,
   trade_id BIGINT REFERENCES trade (id) ON UPDATE CASCADE ON DELETE CASCADE
);