package ru.otus.homework.configs;

public interface DBCreate
{
    String TBL_CREATE_AUTHOR =
        "CREATE TABLE IF NOT EXISTS author (\n"
        + "  author_id  BIGINT      NOT NULL AUTO_INCREMENT,\n"
        + "  first_name VARCHAR(60) NOT NULL,\n"
        + "  last_name  VARCHAR(40) NOT NULL,\n"
        + "  UNIQUE (first_name, last_name),\n"
        + "  PRIMARY KEY (author_id)\n"
        + ");";
    String TBL_DROP_AUTHOR = "DROP TABLE IF EXISTS author;";

    String TBL_CREATE_BOOK =
        "CREATE TABLE IF NOT EXISTS book (\n"
        + "  book_id        BIGINT      NOT NULL AUTO_INCREMENT,\n"
        + "  isbn           VARCHAR(20) NOT NULL,\n"
        + "  title          VARCHAR(90) NOT NULL,\n"
        + "  edition_number INT,\n"
        + "  copyright      VARCHAR(40) NOT NULL,\n"
        + "  genre_id       BIGINT REFERENCES genre (genre_id),\n"
        + "  UNIQUE (isbn),\n"
        + "  PRIMARY KEY (book_id)"
        + ");";
    String TBL_DROP_BOOK = "DROP TABLE IF EXISTS book;";

    String TBL_CREATE_AUTHOR_ISBN =
        "CREATE TABLE IF NOT EXISTS author_isbn (\n"
        + "  author_id BIGINT NOT NULL REFERENCES author (author_id),\n"
        + "  book_id   BIGINT NOT NULL REFERENCES book (book_id)"
        + ");";
    String TBL_DROP_AUTHOR_ISBN = "DROP TABLE IF EXISTS author_isbn;";

    String TBL_CREATE_GENRE =
        "CREATE TABLE IF NOT EXISTS genre (\n"
        + "  genre_id BIGINT      NOT NULL AUTO_INCREMENT,\n"
        + "  value    VARCHAR(90) NOT NULL,\n"
        + "  UNIQUE (value),\n"
        + "  PRIMARY KEY (genre_id)"
        + ");";
    String TBL_DROP_GENRE = "DROP TABLE IF EXISTS genre;";

    String TBL_CREATE_BOOK_REVIEW =
        "CREATE TABLE IF NOT EXISTS book_review (\n"
        + "  review_id BIGINT       NOT NULL AUTO_INCREMENT,\n"
        + "  review    VARCHAR(255) NOT NULL,\n"
        + "  book_id   BIGINT REFERENCES book (book_id),\n"
        + "  PRIMARY KEY (review_id)"
        + ");";
    String TBL_DROP_BOOK_REVIEW = "DROP TABLE IF EXISTS book_review;";

    String[] DDL_CREATE_OPERATIONS = new String[]{
        TBL_CREATE_AUTHOR,
        // TBL_CREATE_GENRE,
        // TBL_CREATE_BOOK,
        // TBL_CREATE_AUTHOR_ISBN,
        // TBL_CREATE_BOOK_REVIEW,
    };

    String[] DDL_DESTROY_OPERATIONS = new String[]{
        TBL_DROP_AUTHOR_ISBN,
        TBL_DROP_AUTHOR,
        TBL_DROP_BOOK_REVIEW,
        TBL_DROP_BOOK,
        TBL_DROP_GENRE,
    };
}
