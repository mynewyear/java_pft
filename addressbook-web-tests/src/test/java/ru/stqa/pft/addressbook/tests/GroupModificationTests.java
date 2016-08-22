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
        if(app.groups().all().size() == 0){
            app.groups().create(new GroupData().withName("test3"));
        }
    }

    @Test
    public void testGroupModification(){

        //count before test
        Set<GroupData> before = app.groups().all();
        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test3").withFooter("more than test");

        app.groups().modify(group);
        app.goTo().groupPage();
        //count
        Set<GroupData> after = app.groups().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedGroup);
        before.add(group);
        Assert.assertEquals(before, after);
    }


}
