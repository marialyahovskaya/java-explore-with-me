package ru.practicum.ewm.comment.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.dto.NewCommentDto;
import ru.practicum.ewm.comment.service.CommentService;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class PrivateCommentController {

    private final CommentService commentService;

    @PostMapping("/events/{eventId}/comment")
    public ResponseEntity<Object> createComment(@PathVariable Long userId,
                                                @PathVariable Long eventId,
                                                @RequestBody @Valid NewCommentDto commentDto) {
        return new ResponseEntity<>(commentService.addComment(userId, eventId, commentDto), HttpStatus.CREATED);
    }
}
