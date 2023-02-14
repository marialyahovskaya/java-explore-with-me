package ru.practicum.ewm.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.comment.dto.CommentFullDto;
import ru.practicum.ewm.comment.dto.NewCommentDto;
import ru.practicum.ewm.comment.service.CommentService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/comments")
public class AdminCommentController {

    private final CommentService commentService;

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteCommentByAdmin(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<CommentFullDto> patchComment(@PathVariable Long commentId,
                                                       @RequestBody @Valid NewCommentDto commentDto) {
        return new ResponseEntity<>(commentService.patchCommentByAdmin(commentId, commentDto), HttpStatus.OK);
    }
}
