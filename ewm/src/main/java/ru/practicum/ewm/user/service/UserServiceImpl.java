package ru.practicum.ewm.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.practicum.ewm.PaginationHelper;
import ru.practicum.ewm.user.User;
import ru.practicum.ewm.user.UserMapper;
import ru.practicum.ewm.user.UserRepository;
import ru.practicum.ewm.user.dto.NewUserRequest;
import ru.practicum.ewm.user.dto.UserDto;

import java.util.Collection;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public UserDto addUser(final NewUserRequest userDto) {

        User user = UserMapper.toUser(userDto);
        return UserMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public Collection<UserDto> findUsers(Long[] ids, Integer from, Integer size) {
        Pageable pageable = PaginationHelper.makePageable(from, size);
        if (ids != null && ids.length > 0) {
            return UserMapper.toUserDto(userRepository.findByIdIn(ids, pageable));
        } else {
            return UserMapper.toUserDto(userRepository.findAll(pageable).getContent());
        }
    }

}

