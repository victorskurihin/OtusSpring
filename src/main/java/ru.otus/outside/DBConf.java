package ru.otus.outside;

public interface DBConf
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

    String[] DDL_CREATE_OPERATIONS = new String[]{
        TBL_CREATE_AUTHOR,
    };

    String[] DDL_DESTROY_OPERATIONS = new String[]{
        TBL_DROP_AUTHOR,
    };
}
