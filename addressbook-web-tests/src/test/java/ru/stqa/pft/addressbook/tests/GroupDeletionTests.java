package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;
import java.util.regex.Matcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

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
    public void testGroupDeletion() {

        //count before
        Groups before = app.group().all();
        GroupData deletedGroup = before.iterator().next();

        app.group().delete(deletedGroup);
        //count
        Groups after = app.group().all();
        assertThat(after.size(), equalTo(before.size() - 1 ));
        assertThat(after, equalTo(before.withOut(deletedGroup)));
    }
}
