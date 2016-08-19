package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

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
    public void testGroupDeletion() {

        //count before
        List<GroupData> before = app.groups().list();

        int index = before.size() - 1;

        app.groups().delete(index);
        //count
        List<GroupData> after = app.groups().list();
        Assert.assertEquals(after.size(), before.size() - 1 );

        before.remove(index);
        Assert.assertEquals(after, before);

    }
}
