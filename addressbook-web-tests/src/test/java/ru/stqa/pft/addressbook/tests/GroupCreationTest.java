package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() {
        //go to group page
        app.goTo().groupPage();
        //count before
        Groups before = app.group().all();
        //test
        GroupData group = new GroupData().withName("test1").withHeader("test2").withFooter("test3");
        app.group().create(group);
        app.goTo().groupPage();
        assertThat(app.group().count(), equalTo(before.size() + 1));
        Groups after = app.group().all();
        assertThat(after, equalTo(
                before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test
    public void bedGroupCreation() {

        app.goTo().groupPage();
        Groups before = app.group().all();
        GroupData group = new GroupData().withName("test'").withHeader("test2").withFooter("test3");
        app.group().create(group);
        app.goTo().groupPage();
        assertThat(app.group().count(), equalTo(before.size()));
        Groups after = app.group().all();
        assertThat(after, equalTo(before));
    }
}
