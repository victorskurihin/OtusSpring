package ru.otus.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("book")
public class Book implements Serializable, DataSet
{
    static final long serialVersionUID = -2L;

    @Id
    @Column("book_id")
    private long id;

    @Column
    private String isbn;

    @Column
    private String title;

    @Column("edition_number")
    private int editionNumber;

    @Column
    private String copyright;

    private long genreId;

    private List<Author> authors = new LinkedList<>();

    private Genre genre;
}
