package com.ehr.userservice.mappers;

import com.ehr.userservice.dto.AuthUserDto;
import com.ehr.userservice.dto.UserDto;
import com.ehr.userservice.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserDto mapToUserDTO(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setPhone(user.getPhone());
        userDto.setRole(user.getRole());
        userDto.setStatus(user.getStatus());
        return userDto;
    }

    public AuthUserDto mapToAuthUserDto(User user) {
        AuthUserDto authUserDto = new AuthUserDto();
        authUserDto.setUserId(user.getId());
        authUserDto.setUsername(user.getUsername());
        authUserDto.setEmail(user.getEmail());
        authUserDto.setPhone(user.getPhone());
        authUserDto.setPassword(user.getPassword());
        authUserDto.setRole(user.getRole());
        authUserDto.setStatus(user.getStatus());
        return authUserDto;
    }

}
