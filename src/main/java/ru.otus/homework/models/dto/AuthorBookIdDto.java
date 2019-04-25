package ru.otus.homework.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
<<<<<<< HEAD
=======
import lombok.EqualsAndHashCode;
>>>>>>> 3c01fc04fe97660798e73fdae8736623943ae16e
import lombok.NoArgsConstructor;
import ru.otus.homework.models.Author;

@Data
@NoArgsConstructor
@AllArgsConstructor
<<<<<<< HEAD
=======
@EqualsAndHashCode(callSuper = true)
>>>>>>> 3c01fc04fe97660798e73fdae8736623943ae16e
public class AuthorBookIdDto extends AuthorDto
{
    private Long bookId;

    public AuthorBookIdDto(Author author)
    {
        super(author);
        bookId = 0L;
    }
}
