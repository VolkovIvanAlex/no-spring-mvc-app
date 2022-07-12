package network.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import network.dao.RowMapper;
import network.dto.UserDto;

public class UserDtoMapper implements RowMapper<UserDto> {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public UserDto map(ResultSet rs) throws SQLException {
        UserDto userDto = new UserDto();
        userDto.setLogin(rs.getString(LOGIN));
        userDto.setPassword(rs.getString(PASSWORD));
        return userDto;
    }
    
}
