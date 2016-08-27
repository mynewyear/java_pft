package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.*;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by IEUser on 8/1/2016.
 */
public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        //go to group page
        app.goTo().groupPage();
        //check
        if(app.group().all().size() == 0){
            app.group().create(new GroupData().withName("test3"));
        }
    }

    @Test
    public void testGroupModification(){


        //count before test
        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();

        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test3").withFooter("more than test");

        app.group().modify(group);
        app.goTo().groupPage();
        //count
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size()));

        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }
}
