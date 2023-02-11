package ru.practicum.ewm.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import ru.practicum.ewm.user.dto.UserDto;
import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDto {

    private Long id;

    private String text;

    private UserDto author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdOn;
}
