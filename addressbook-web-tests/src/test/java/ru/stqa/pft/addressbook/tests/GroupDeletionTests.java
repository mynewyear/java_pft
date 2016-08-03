package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() {

        app.getNavigationHelper().goToGroupPage();
        app.getGroupsHelper().selectGroup();
        app.getGroupsHelper().deleteSelectedGroup();
        app.getGroupsHelper().returnToGroupPage();
    }

}