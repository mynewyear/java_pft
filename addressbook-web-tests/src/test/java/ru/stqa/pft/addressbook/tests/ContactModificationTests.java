package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

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
    public void contactModificationTests() {
        //count before test
        Set<ContactData> before = app.contacts().all();
        ContactData modifiedContact = before.iterator().next();
 //       int index = before.size() - 1;
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("NataLia")
                .withLastName("LastName").withAddress("world");

        app.contacts().modify(contact);

        //count
        Set<ContactData> after = app.contacts().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(modifiedContact);
        before.add(contact);
        Assert.assertEquals(before, after);
    }
}