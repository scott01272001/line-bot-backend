CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE role (
    id UUID DEFAULT uuid_generate_v4(),
    name VARCHAR(255) PRIMARY KEY NOT NULL
);

CREATE TABLE account (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role_name VARCHAR(255) NOT NULL,
  FOREIGN KEY (role_name) REFERENCES role(name) ON DELETE RESTRICT ON UPDATE CASCADE
);



INSERT INTO role (name) VALUES ('admin');
INSERT INTO role (name) VALUES ('user');
INSERT INTO account (username, password, role_name) VALUES ('admin', 'password', 'admin');

