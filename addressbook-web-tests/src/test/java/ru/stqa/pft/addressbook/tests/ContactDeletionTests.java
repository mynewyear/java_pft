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
            app.contact().create(new ContactData().withName("Nata").withLastName("LastName")
                    .withNick("nick").withTitle("tester").withCompany("company")
                    .withAddress("Russia").withPhone("1234567890").withEmail("test1@gmail.com")
                    .withByear("1990").withNotes("so many notes").withGroup("test1"));
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
