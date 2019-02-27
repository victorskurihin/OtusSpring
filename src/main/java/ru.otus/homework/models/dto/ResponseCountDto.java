package ru.otus.homework.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseCountDto
{
    private final String count;

    public ResponseCountDto(long count)
    {
        this.count = Long.toString(count);
    }
}
