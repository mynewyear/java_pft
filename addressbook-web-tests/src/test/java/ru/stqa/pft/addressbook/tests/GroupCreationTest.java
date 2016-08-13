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


 //       Comparator<? super GroupData> byId = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());
 //       int max1 = after.stream().max ((o1, o2)-> Integer.compare(o1.getId(), o2.getId())).get().getId();
        group.setId(after.stream().max ((o1, o2)-> Integer.compare(o1.getId(), o2.getId())).get().getId());
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}
