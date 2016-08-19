package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData("Nata", "LastName", "Nata",
                    "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                    "so many notes", "test3"));
        }
    }
    
    @Test
    public void contactDeletionTests() {
        //count
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().delete(index);
        //count
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1 );

        before.remove(index);
        Assert.assertEquals(after, before);
    }
}
