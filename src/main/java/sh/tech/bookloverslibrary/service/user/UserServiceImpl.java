package sh.tech.bookloverslibrary.service.user;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sh.tech.bookloverslibrary.dao.entity.User;
import sh.tech.bookloverslibrary.dao.repository.UserRepository;
import sh.tech.bookloverslibrary.exception.UserNotFoundException;
import sh.tech.bookloverslibrary.mapper.user.UserMapper;
import sh.tech.bookloverslibrary.model.dto.user.UserRequestDto;
import sh.tech.bookloverslibrary.model.dto.user.UserResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    UserMapper mapper;
    PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponseDto> getAllUser() {
        return userRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User Not Found by id " + id));
        return mapper.toDto(user);
    }

    @Override
    public void updateUser(Long id, UserRequestDto userRequestDto) {
        User user = mapper.toEntity(userRequestDto);
        user.setId(id);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User Not Found by id " + id));
        userRepository.delete(user);
    }

}

