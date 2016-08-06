package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() {

        //go to group page
        app.getNavigationHelper().goToGroupPage();
        //count before
        int before = app.getGroupsHelper().getGroupCount();
        //test
        app.getGroupsHelper().createGroup(new GroupData("test1", "test2", "test3"));
//        app.getGroupsHelper().initGroupCreation();
//        app.getGroupsHelper().fillGroupForm(new GroupData("test1", "test2", "test3"));
//        app.getGroupsHelper().submitGroupCreation();
//        app.getGroupsHelper().returnToGroupPage();
        //count
        int after = app.getGroupsHelper().getGroupCount();
        Assert.assertEquals(after, before + 1 );
    }

}
