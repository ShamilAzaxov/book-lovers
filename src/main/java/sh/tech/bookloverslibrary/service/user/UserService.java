package sh.tech.bookloverslibrary.service.user;



import sh.tech.bookloverslibrary.model.dto.user.UserRequestDto;
import sh.tech.bookloverslibrary.model.dto.user.UserResponseDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> getAllUser();
    UserResponseDto getUserById(Long id);
    void updateUser(Long id, UserRequestDto userRequestDto);
    void deleteUser(Long id);
}
