package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification(){
        app.getNavigationHelper().goToGroupPage();
        if(!app.getGroupsHelper().isThereGroup()){
            app.getGroupsHelper().createGroup(new GroupData("test1", null,null));
        }
        app.getGroupsHelper().selectGroup();
        app.getGroupsHelper().innitGroupModification();
        app.getGroupsHelper().fillGroupForm(new GroupData("test3",null,null));
        app.getGroupsHelper().submitGroupModification();
        app.getNavigationHelper().goToGroupPage();
    }
}
