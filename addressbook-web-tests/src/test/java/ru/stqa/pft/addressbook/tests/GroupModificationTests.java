package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.*;

/**
 * Created by IEUser on 8/1/2016.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        //go to group page
        app.goTo().groupPage();
        //check
        if(app.groups().list().size() == 0){
            app.groups().create(new GroupData().withName("test3"));
        }
    }

    @Test
    public void testGroupModification(){

        //count before test
        List<GroupData> before = app.groups().list();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("test3").withHeader("test2").withFooter("more than test");

        app.groups().modify(group, index);
        app.goTo().groupPage();
        //count
        List<GroupData> after = app.groups().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }


}
