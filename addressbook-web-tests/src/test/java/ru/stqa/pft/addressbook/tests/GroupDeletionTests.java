package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

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
    public void testGroupDeletion() {

        //count before
        Set<GroupData> before = app.groups().all();
        GroupData deletedGroup = before.iterator().next();
//        int index = before.size() - 1;

        app.groups().delete(deletedGroup);
        //count
        Set<GroupData> after = app.groups().all();
        Assert.assertEquals(after.size(), before.size() - 1 );

        before.remove(deletedGroup);
        Assert.assertEquals(after, before);

    }
}
