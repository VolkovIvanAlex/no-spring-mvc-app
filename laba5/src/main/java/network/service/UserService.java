package network.service;

import network.dto.JwtTokenDto;
import network.dto.UserDto;

public interface UserService {
    JwtTokenDto loginUserAndReturnJwt(final UserDto credentials);

    UserDto createNewUser(final UserDto userDto);

    boolean validateUserJwt(final JwtTokenDto jwtTokenDto);
}
