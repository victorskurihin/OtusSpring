package ru.otus.homework.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.otus.homework.models.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorBookIdDto
{
    private String id;

    private String bookId;

    private String firstName;

    private String lastName;

    public AuthorBookIdDto(Author author)
    {
        id = Long.toString(author.getId());
        bookId = "0";
        firstName = author.getFirstName();
        lastName = author.getLastName();
    }
}
