package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() {
        app.getNavigationHelper().goToGroupPage();
        app.getGroupsHelper().createGroup(new GroupData("test1", "test2", "test3"));
//        app.getGroupsHelper().initGroupCreation();
//        app.getGroupsHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
//        app.getGroupsHelper().submitGroupCreation();
//        app.getGroupsHelper().returnToGroupPage();
    }

}
