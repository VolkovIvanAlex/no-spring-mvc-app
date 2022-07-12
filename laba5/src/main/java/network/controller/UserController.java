package network.controller;

import network.annotation.RequestBody;
import network.annotation.RequestMapping;
import network.annotation.RequestMethod;
import network.annotation.RestController;
import network.dto.JwtTokenDto;
import network.dto.UserDto;
import network.service.UserService;
import network.service.implementation.DefaultUserService;

@RestController
public class UserController {

    UserService userService = new DefaultUserService();

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public JwtTokenDto loginUser(@RequestBody UserDto credentials) {
        return userService.loginUserAndReturnJwt(credentials);
    }
}
