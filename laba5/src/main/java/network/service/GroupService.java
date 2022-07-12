package network.service;

import java.util.List;

import network.dto.GroupDto;


public interface GroupService {
    List<GroupDto> getAllGroups();

    GroupDto addGroup(GroupDto groupDto);

	void updateGroup(Integer id, GroupDto groupDto);

	void deleteGroupById(Integer id);
}
