package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
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

        GroupData group = new GroupData(before.get(before.size() -1).getId(), "test3",null,null);
        app.getGroupsHelper().fillGroupForm(group);
        app.getGroupsHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupPage();
        //count
        List<GroupData> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1 );
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
    }
}
