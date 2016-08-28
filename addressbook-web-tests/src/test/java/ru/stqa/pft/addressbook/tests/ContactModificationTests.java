package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

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
            Contacts before = app.contacts().all();
            ContactData modifiedContact = before.iterator().next();
            ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("NataLia")
                    .withLastName("LastName").withAddress("world");

            app.contacts().modify(contact);
            app.goTo().homePage();
            //count
            Contacts after = app.contacts().all();
            assertEquals(after.size(), before.size());

            assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

        }
}