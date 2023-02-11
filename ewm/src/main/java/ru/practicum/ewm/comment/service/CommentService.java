package ru.practicum.ewm.comment.service;

import ru.practicum.ewm.comment.dto.CommentDto;
import ru.practicum.ewm.comment.dto.NewCommentDto;

public interface CommentService {

    CommentDto addComment(Long userId, Long eventId, NewCommentDto commentDto);

}
