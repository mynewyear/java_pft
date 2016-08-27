package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
        System.out.println(before);
        ContactData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact);
       app.goTo().homePage();
        //count
        System.out.println(before.size());

        Contacts after = app.contacts().all();
        System.out.println(after.size());
        assertEquals(after.size(), before.size() - 1 );

        assertThat(after, equalTo(before.withOut(deletedContact)));
    }
}
