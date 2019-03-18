package ru.otus.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

=======

import javax.persistence.*;
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
<<<<<<< HEAD
@Table("book_review")
=======
@Entity
@Table(name = "book_review")
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
public class Review implements Serializable, DataSet
{
    static final long serialVersionUID = -3L;

    @Id
<<<<<<< HEAD
    private long id;

    private String review;

=======
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String review;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "book_id", nullable = false)
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
    private Book book;
}
