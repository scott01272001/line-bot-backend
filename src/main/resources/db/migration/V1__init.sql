CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE account (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL
);

INSERT INTO account (username, password) VALUES ('admin', 'password');
