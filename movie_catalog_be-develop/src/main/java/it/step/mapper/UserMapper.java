package it.step.mapper;

import it.step.dto.UserDTO;
import it.step.entity.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    User userDtoToUser(UserDTO userDto);
    UserDTO userToUserDto(User user);

}
