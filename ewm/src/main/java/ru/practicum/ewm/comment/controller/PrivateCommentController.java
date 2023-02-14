package ru.practicum.ewm.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.dto.CommentFullDto;
import ru.practicum.ewm.comment.dto.NewCommentDto;
import ru.practicum.ewm.comment.service.CommentService;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class PrivateCommentController {

    private final CommentService commentService;

    @GetMapping("/comments")
    public ResponseEntity<Collection<CommentFullDto>> findComments(@PathVariable Long userId,
                                                                   @RequestParam Long eventId) {
        return new ResponseEntity<>(commentService.findCommentsByUserAndEvent(userId, eventId), HttpStatus.OK);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentFullDto> findComment(@PathVariable Long userId,
                                                      @PathVariable Long commentId) {
        return new ResponseEntity<>(commentService.findCommentForAuthor(userId, commentId), HttpStatus.OK);
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentFullDto> createComment(@PathVariable Long userId,
                                                        @RequestParam Long eventId,
                                                        @RequestBody @Valid NewCommentDto commentDto) {
        return new ResponseEntity<>(commentService.addComment(userId, eventId, commentDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long userId, @PathVariable Long commentId) {
        commentService.deleteComment(userId, commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<CommentFullDto> patchComment(@PathVariable Long userId,
                                                       @PathVariable Long commentId,
                                                       @RequestBody @Valid NewCommentDto commentDto) {
        return new ResponseEntity<>(commentService.patchComment(userId, commentId, commentDto), HttpStatus.OK);
    }
}
