package network.service.implementation;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import network.dao.GroupDao;
import network.dao.implementation.DefaultGroupDao;
import network.dto.GroupDto;
import network.exception.ResponseErrorException;
import network.service.GroupService;

public class DefaultGroupService implements GroupService {
    private GroupDao groupDao = new DefaultGroupDao();

    @Override
    public List<GroupDto> getAllGroups() {
        try {
            List<GroupDto> result = groupDao.getAllGroups();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseErrorException(500);
        }
    }

    @Override
    public GroupDto addGroup(GroupDto groupDto) {
        try {
            if (groupDto.getName() == null) {
                throw new ResponseErrorException(409, "Group name is required");
            }
            if (groupDao.getGroupByName(groupDto.getName()).isPresent()) {
                throw new ResponseErrorException(409, "Group with such name already exists");
            }
            int rowCount = groupDao.insertNewGroup(groupDto);
            if (rowCount == 1) {
                return groupDao.getGroupByName(groupDto.getName()).orElseThrow();
            } else {
                throw new ResponseErrorException(500);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseErrorException(500);
        }
    }

    @Override
    public void updateGroup(Integer id, GroupDto groupDto) {
        try {
            if (groupDto.getName() == null) {
                throw new ResponseErrorException(409, "Group name is required");
            }
            Optional<GroupDto> existed = groupDao.getGroupById(id);
            Optional<GroupDto> existedName = groupDao.getGroupByName(groupDto.getName());
            if (existed.isPresent()) {
                if (existedName.isPresent() && !existed.equals(existedName)) {
                    throw new ResponseErrorException(409, "Group name is already in use");
                }
                groupDto.setId(id);
                if (!groupDto.equals(existed.get())) {
                    groupDao.updateGroupInfo(groupDto);
                }
            } else {
                throw new ResponseErrorException(404, "Group with such id is not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseErrorException(500);
        }
    }

    @Override
    public void deleteGroupById(Integer id) {
        try {
            Optional<GroupDto> existed = groupDao.getGroupById(id);
            if (existed.isPresent()) {
                groupDao.deleteGroupById(id);
            } else {
                throw new ResponseErrorException(404, "Group with such id is not found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ResponseErrorException(500);
        }
    }
}
