package ru.practicum.ewm.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.ewm.user.dto.NewUserRequest;
import ru.practicum.ewm.user.dto.UserDto;
import ru.practicum.ewm.user.service.UserService;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.Collection;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/users")
public class UserController {

    private final UserService userService;



    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid NewUserRequest userDto) throws ValidationException {
        return new ResponseEntity<>(userService.addUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Collection<UserDto>> findUsers(@RequestParam(required = false) Long[] ids,
                                                         @RequestParam(required = false, defaultValue = "0") Integer from,
                                                         @RequestParam(required = false, defaultValue = "10") Integer size) {
        return new ResponseEntity<>(userService.findUsers(ids, from, size), HttpStatus.OK);
    }

}
