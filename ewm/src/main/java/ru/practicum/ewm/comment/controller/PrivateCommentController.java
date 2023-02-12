package ru.practicum.ewm.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.dto.CommentDto;
import ru.practicum.ewm.comment.dto.NewCommentDto;
import ru.practicum.ewm.comment.service.CommentService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/users/{userId}")
public class PrivateCommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long userId,
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
    public ResponseEntity<CommentDto> patchComment(@PathVariable Long userId,
                                                   @PathVariable Long commentId,
                                                   @RequestBody @Valid NewCommentDto commentDto) {
        return new ResponseEntity<>(commentService.patchComment(userId, commentId, commentDto), HttpStatus.OK);
    }
}
