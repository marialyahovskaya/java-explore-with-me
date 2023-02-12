package ru.practicum.ewm.comment;

import ru.practicum.ewm.comment.dto.CommentDto;
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

    public static CommentDto toCommentDto(Comment comment) {

        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setAuthor(UserMapper.toUserDto(comment.getAuthor()));
        commentDto.setText(comment.getText());
        commentDto.setCreatedOn(comment.getCreatedOn());

        return commentDto;
    }

    public static Collection<CommentDto> toCommentDto(Collection<Comment> comments) {
        return comments.stream()
                .map(CommentMapper::toCommentDto)
                .collect(Collectors.toUnmodifiableList());
    }


}
