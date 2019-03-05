package ru.otus.homework.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Table("author")
public class Author implements Serializable, DataSet
{
    static final long serialVersionUID = -1L;

    @Id
    @Column("author_id")
    private long id;

    @Column("first_name")
    private String firstName;

    @Column("last_name")
    private String lastName;
}