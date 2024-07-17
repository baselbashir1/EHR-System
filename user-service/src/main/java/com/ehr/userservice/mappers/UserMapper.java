package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.AuthUserDto;
import com.ehr.userservice.dto.UserDto;
import com.ehr.userservice.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto mapToUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
      //  userDto.setUserDetails(user.getUserInformation());
        return userDto;
    }

    public AuthUserDto mapToAuthUserDto(User user) {
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setId(user.getId());
        authUserDto.setUsername(user.getUsername());
        authUserDto.setPassword(user.getPassword());
        authUserDto.setRole(user.getRole());
        return authUserDto;
    }

}
