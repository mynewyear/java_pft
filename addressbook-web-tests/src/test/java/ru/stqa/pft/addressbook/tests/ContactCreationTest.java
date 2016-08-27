package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends  TestBase{


    @Test
    public void contactCreationTest() {

            app.goTo().homePage();
            //count before
            Contacts before = app.contacts().all();
            ContactData contact = new ContactData().withName("Nata").withLastName("LastName")
                    .withNick("nick").withTitle("tester").withCompany("company")
                    .withAddress("Russia").withPhone("1234567890").withEmail("test1@gmail.com")
                    .withByear("1990").withNotes("so many notes");
            app.contacts().create(contact);
            app.goTo().homePage();
            //count
            Contacts after = app.contacts().all();
            assertThat(app.contacts().count(), equalTo(before.size() + 1));

            assertThat(after, equalTo(
                    before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }

}