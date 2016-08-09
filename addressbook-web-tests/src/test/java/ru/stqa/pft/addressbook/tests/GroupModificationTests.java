package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

/**
 * Created by IEUser on 8/1/2016.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        //go to group page
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupsHelper().isThereGroup()){
            app.getGroupsHelper().createGroup(new GroupData("test1", null,null));
        }
        //count before
        List<GroupData> before = app.getGroupsHelper().getGroupList();
        app.getGroupsHelper().selectGroup(before.size() - 1);
        app.getGroupsHelper().innitGroupModification();
        app.getGroupsHelper().fillGroupForm(new GroupData("test3",null,null));
        app.getGroupsHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupPage();
        //count
        List<GroupData> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

    }
}
