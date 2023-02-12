package ru.practicum.ewm.comment;

import ru.practicum.ewm.comment.dto.CommentFullDto;
import ru.practicum.ewm.comment.dto.CommentShortDto;
import ru.practicum.ewm.comment.dto.NewCommentDto;
import ru.practicum.ewm.event.Event;
import ru.practicum.ewm.user.User;
import ru.practicum.ewm.user.UserMapper;

import java.util.Collection;
import java.util.stream.Collectors;


public class CommentMapper {

    public static Comment toComment(NewCommentDto commentDto, User user, Event event) {
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setAuthor(user);
        comment.setEvent(event);

        return comment;
    }

    public static CommentShortDto toCommentShortDto(Comment comment) {
        CommentShortDto commentShortDto = new CommentShortDto();
        commentShortDto.setId(comment.getId());
        commentShortDto.setAuthor(UserMapper.toUserDto(comment.getAuthor()));
        commentShortDto.setText(comment.getText());
        commentShortDto.setCreatedOn(comment.getCreatedOn());

        return commentShortDto;
    }

    public static Collection<CommentShortDto> toCommentShortDto(Collection<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toCommentShortDto)
                .collect(Collectors.toUnmodifiableList());
    }

    public static CommentFullDto toCommentFullDto(Comment comment) {
        CommentFullDto commentFullDto = new CommentFullDto();
        commentFullDto.setId(comment.getId());
        commentFullDto.setAuthor(UserMapper.toUserDto(comment.getAuthor()));
        commentFullDto.setText(comment.getText());
        commentFullDto.setCreatedOn(comment.getCreatedOn());
        commentFullDto.setEventId(comment.getEvent().getId());

        return commentFullDto;
    }

    public static Collection<CommentFullDto> toCommentFullDto(Collection<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toCommentFullDto)
                .collect(Collectors.toUnmodifiableList());
    }
}
