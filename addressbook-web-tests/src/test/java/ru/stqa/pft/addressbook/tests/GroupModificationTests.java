package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        //go to group page
        app.getNavigationHelper().goToGroupPage();
        //count before
        int before = app.getGroupsHelper().getGroupCount();

        if(!app.getGroupsHelper().isThereGroup()){
            app.getGroupsHelper().createGroup(new GroupData("test1", null,null));
        }
        app.getGroupsHelper().selectGroup(before - 1);
        app.getGroupsHelper().innitGroupModification();
        app.getGroupsHelper().fillGroupForm(new GroupData("test3",null,null));
        app.getGroupsHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupPage();
        //count
        int after = app.getGroupsHelper().getGroupCount();
        Assert.assertEquals(after, before);

    }
}
