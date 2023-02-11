package ru.practicum.ewm.comment;

import ru.practicum.ewm.comment.dto.CommentDto;
import ru.practicum.ewm.comment.dto.NewCommentDto;
import ru.practicum.ewm.event.Event;
import ru.practicum.ewm.user.User;
import ru.practicum.ewm.user.UserMapper;


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

//    Category toCategory(NewCategoryDto category);
//
//    Collection<Category> toCategory(Collection<CategoryDto> category);
//
//    CategoryDto toCategoryDto(Category categories);
//
//    Collection<CategoryDto> toCategoryDto(Collection<Category> categories);
}
