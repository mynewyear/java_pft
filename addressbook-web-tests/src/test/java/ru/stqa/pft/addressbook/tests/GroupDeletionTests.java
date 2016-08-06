package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {

        //go to group page
        app.getNavigationHelper().goToGroupPage();
        //count before
        int before = app.getGroupsHelper().getGroupCount();
        //test
        if(!app.getGroupsHelper().isThereGroup()){
            app.getGroupsHelper().createGroup(new GroupData("test1", null,null));
        }
        app.getGroupsHelper().selectGroup(0);
        app.getGroupsHelper().deleteSelectedGroup();
        app.getGroupsHelper().returnToGroupPage();
        //count after
        int after = app.getGroupsHelper().getGroupCount();
        //count's result
        Assert.assertEquals(after, before - 1 );
    }

}
