package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() {

        //go to group page
        app.goTo().groupPage();
        //count before
        Set<GroupData> before = app.groups().all();
        //test
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.groups().create(group);
        //count
        Set<GroupData> after = app.groups().all();
        Assert.assertEquals(after.size(), before.size() + 1 );

        group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
        before.add(group);
        Assert.assertEquals(before, after);
    }

}
