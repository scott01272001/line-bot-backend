CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE role (
    id BIGINT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE account (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  role_id BIGINT NOT NULL,
  FOREIGN KEY (role_id) REFERENCES role(id) ON DELETE RESTRICT ON UPDATE CASCADE
);



INSERT INTO role (id, name) VALUES (1, 'admin');
INSERT INTO role (id, name) VALUES (2, 'user');
INSERT INTO account (username, password, role_id) VALUES ('admin', '{bcrypt}$2a$10$08A0t12Y65sx2fDJMBWMxuxFAHikwm1Vn4KVmlFzv3RMyFBmZrYdC', 1);

