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
                    .withAddress("Russia").withPhone("1234567890").withMobile("22-2").withWorkPhone("3(333)33").withEmail("test1@gmail.com")
                    .withEmail2("123@fgg.cv").withEmail3("34567"));
        }
    }

        @Test
        public void contactModificationTests() {

            Contacts before = app.contacts().all();
            ContactData modifiedContact = before.iterator().next();
            ContactData contact = new ContactData().withId(modifiedContact.getId()).withName("NataLia")
                    .withLastName("LastName").withAddress("world");
            app.contacts().modify(contact);
            app.goTo().homePage();
            assertEquals(app.group().count(), before.size());
            Contacts after = app.contacts().all();
            assertThat(after, equalTo(before.withOut(modifiedContact).withAdded(contact)));

        }
}