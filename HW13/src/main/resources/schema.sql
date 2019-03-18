DROP TABLE IF EXISTS books_genres;
DROP TABLE IF EXISTS  books_authors;
DROP TABLE IF EXISTS  books_comments;
DROP TABLE IF EXISTS  books;
DROP TABLE IF EXISTS  genres;
DROP TABLE IF EXISTS  authors;
DROP TABLE IF EXISTS  users;

DROP TABLE IF EXISTS  acl_sid;
DROP TABLE IF EXISTS  acl_class;
DROP TABLE IF EXISTS  acl_object_identity;
DROP TABLE IF EXISTS  acl_entry;

CREATE TABLE authors(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255));
CREATE TABLE genres(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255));
CREATE TABLE books(id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), description VARCHAR(8000), pub_year INT);
CREATE TABLE books_authors(book_id BIGINT, author_id BIGINT, FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE, FOREIGN KEY(author_id) REFERENCES authors(id) ON DELETE CASCADE);
CREATE TABLE books_genres(book_id BIGINT, genre_id BIGINT, FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE, FOREIGN KEY(genre_id) REFERENCES genres(id) ON DELETE CASCADE);
CREATE TABLE books_comments(id BIGINT AUTO_INCREMENT PRIMARY KEY, book_id BIGINT, commenting_time DATETIME DEFAULT CURRENT_TIMESTAMP, author VARCHAR(255), comment VARCHAR(255), FOREIGN KEY(book_id) REFERENCES books(id) ON DELETE CASCADE);
CREATE TABLE users(id BIGINT AUTO_INCREMENT PRIMARY KEY, login VARCHAR(255), password VARCHAR(255));


CREATE TABLE acl_sid(id BIGINT NOT NULL PRIMARY KEY, principal TINYINT(1) NOT NULL, sid VARCHAR(100) NOT NULL, CONSTRAINT unique_uk_1 UNIQUE(sid,principal));
CREATE TABLE acl_class(id BIGINT NOT NULL PRIMARY KEY, class VARCHAR(100) NOT NULL, CONSTRAINT unique_uk_2 UNIQUE(class));

CREATE TABLE acl_object_identity(id BIGINT PRIMARY KEY,
    object_id_class BIGINT NOT NULL,
    object_id_identity BIGINT NOT NULL,
    parent_object BIGINT,
    owner_sid BIGINT,
	entries_inheriting TINYINT(1) NOT NULL,
	CONSTRAINT unique_uk_3 UNIQUE(object_id_class,object_id_identity),
	CONSTRAINT foreign_fk_1 FOREIGN KEY(parent_object)REFERENCES acl_object_identity(id),
	CONSTRAINT foreign_fk_2 FOREIGN KEY(object_id_class)REFERENCES acl_class(id),
	CONSTRAINT foreign_fk_3 FOREIGN KEY(owner_sid)REFERENCES acl_sid(id)
);

CREATE TABLE acl_entry(
	id BIGINT PRIMARY KEY,
	acl_object_identity BIGINT NOT NULL,
	ace_order INT NOT NULL,
	sid BIGINT NOT NULL,
	mask INTEGER NOT NULL,
	granting TINYINT(1) NOT NULL,
	audit_success TINYINT(1) NOT NULL,
	audit_failure TINYINT(1) NOT NULL,
	CONSTRAINT unique_uk_4 UNIQUE(acl_object_identity, ace_order),
	CONSTRAINT foreign_fk_4 FOREIGN KEY(acl_object_identity) REFERENCES acl_object_identity(id),
	CONSTRAINT foreign_fk_5 FOREIGN KEY(sid) REFERENCES acl_sid(id)
);