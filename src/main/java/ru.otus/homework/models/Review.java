package ru.otus.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table("book_review")
public class Review implements Serializable, DataSet
{
    static final long serialVersionUID = -3L;

    @Id
    @Column("review_id")
    private long id;

    @Column
    private String review;

    private Book book;
}
