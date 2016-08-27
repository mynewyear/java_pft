package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

public class ContactDeletionTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withName("Nata").withLastName("LastName")
                    .withNick("nick").withTitle("tester").withCompany("company")
                    .withAddress("Russia").withPhone("1234567890").withEmail("test1@gmail.com")
                    .withByear("1990").withNotes("so many notes"));
        }
    }

    @Test
    public void contactDeletionTests() {
        //count
        Contacts before = app.contacts().all();
        ContactData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact);
        //count
        Contacts after = app.contacts().all();
        Assert.assertEquals(app.contacts().count(), before.size() - 1 );
        //       Assert.assertEquals(after.size(), before.size() - 1);

        MatcherAssert.assertThat(after, CoreMatchers.equalTo(before.withOut(deletedContact)));
    }
}
