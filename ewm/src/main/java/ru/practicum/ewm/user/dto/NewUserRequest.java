package ru.practicum.ewm.user.dto;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NewUserRequest {

    private String name;

    private String email;

}
