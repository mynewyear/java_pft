package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() {

        //go to group page
        app.getNavigationHelper().goToGroupPage();
        //count before
        List<GroupData> before = app.getGroupsHelper().getGroupList();
        //test
        GroupData group = new GroupData("test1", "test2", "test3");
        app.getGroupsHelper().createGroup(group);
        //count
        List<GroupData> after = app.getGroupsHelper().getGroupList();
        Assert.assertEquals(after.size(), before.size() + 1 );

//        group.setId(after.stream().max ((o1, o2)-> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
