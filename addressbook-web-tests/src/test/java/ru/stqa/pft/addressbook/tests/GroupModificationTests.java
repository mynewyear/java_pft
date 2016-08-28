package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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

        Groups before = app.group().all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("test3").withFooter("more than test");
        app.group().modify(group);
        app.goTo().groupPage();
        assertEquals(app.group().count(), before.size());
        Groups after = app.group().all();
        assertThat(after, equalTo(before.withOut(modifiedGroup).withAdded(group)));
    }
}
