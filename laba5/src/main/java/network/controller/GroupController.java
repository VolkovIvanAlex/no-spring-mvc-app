package network.controller;

import java.util.List;

import network.annotation.PathParameter;
import network.annotation.RequestBody;
import network.annotation.RequestMapping;
import network.annotation.RequestMethod;
import network.annotation.RestController;
import network.dto.GroupDto;
import network.service.GroupService;
import network.service.implementation.DefaultGroupService;

@RestController
public class GroupController {

    private GroupService groupService = new DefaultGroupService();

    @RequestMapping(path = "/api/groups", method = RequestMethod.GET)
    public List<GroupDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @RequestMapping(path = "/api/group", method = RequestMethod.PUT)
    public GroupDto addGroup(@RequestBody GroupDto groupDto) {
        return groupService.addGroup(groupDto);
    }

    @RequestMapping(path = "/api/group/{id}", method = RequestMethod.POST)
    public void updateGroup(@RequestBody GroupDto groupDto, @PathParameter("id") String groupId) {
        groupService.updateGroup(Integer.valueOf(groupId), groupDto);
    }

    @RequestMapping(path = "/api/group/{id}", method = RequestMethod.DELETE)
    public void deleteGroup(@PathParameter("id") String groupId) {
        groupService.deleteGroupById(Integer.valueOf(groupId));
    }
}
