# --- !Ups
CREATE TABLE "arch_users" (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  push_token VARCHAR(255)
);

CREATE TABLE "arch_books" (
  id SERIAL PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  book_order integer,
  user_id integer NOT NULL
);

# --- !Downs
DROP TABLE IF EXISTS "arch_users";
DROP TABLE IF EXISTS "arch_books";