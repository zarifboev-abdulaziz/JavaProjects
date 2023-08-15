package uz.pdp.service;

import uz.pdp.model.Group;
import uz.pdp.model.User;

public interface GroupService {

    void myGroups(User user);

    void groups(User user);

    void createGroup(User user);

    void selectedGroupMenu(Group group, User user);

}
