package ru.otus.homework.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
<<<<<<< HEAD
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
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
@Table("author")
=======
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"first_name", "last_name"}))
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
public class Author implements Serializable, DataSet
{
    static final long serialVersionUID = -1L;

    @Id
<<<<<<< HEAD

    private long id;

    private String firstName;

=======
    @Column(name = "author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
>>>>>>> 9eec745064b242dd0bf3b4f8d74f206e073df253
    private String lastName;
}