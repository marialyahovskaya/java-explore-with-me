package ru.practicum.ewm.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.dto.CommentFullDto;
import ru.practicum.ewm.comment.service.CommentService;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/comments")
public class PublicCommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Collection<CommentFullDto>> findComments(@RequestParam Long eventId) {
        return new ResponseEntity<>(commentService.findCommentsByEvent(eventId), HttpStatus.OK);
    }

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentFullDto> findComment(@PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.findComment(commentId), HttpStatus.OK);
    }
}
