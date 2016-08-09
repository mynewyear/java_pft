package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() {

        //go to group page
        app.getNavigationHelper().goToGroupPage();
        //count before
        List<GroupData> before = app.getGroupsHelper().getGroupList();
        //test
        app.getGroupsHelper().createGroup(new GroupData("test1", "test2", "test3"));
        //count
        List<GroupData> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1 );
    }

}
