package ru.practicum.ewm.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.comment.Comment;
import ru.practicum.ewm.comment.CommentMapper;
import ru.practicum.ewm.comment.CommentRepository;
import ru.practicum.ewm.comment.dto.CommentDto;
import ru.practicum.ewm.comment.dto.NewCommentDto;
import ru.practicum.ewm.event.Event;
import ru.practicum.ewm.event.EventRepository;
import ru.practicum.ewm.exception.NotFoundException;
import ru.practicum.ewm.user.User;
import ru.practicum.ewm.user.UserRepository;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final CommentRepository commentRepository;

    @Override
    public CommentDto addComment(Long userId, Long eventId, NewCommentDto commentDto) {

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new NotFoundException("Event not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        Comment comment = CommentMapper.toComment(commentDto, user, event);
        Comment savedComment = commentRepository.save(comment);
        return CommentMapper.toCommentDto(savedComment);
    }
}

