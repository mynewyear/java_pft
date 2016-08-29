package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends  TestBase{


    @Test
    public void contactCreationTest() {

            app.goTo().homePage();
            Contacts before = app.contacts().all();
            ContactData contact = new ContactData().withName("Nata").withLastName("LastName")
                    .withNick("nick").withTitle("tester").withCompany("company")
                    .withAddress("Russia").withPhone("1234567890").withMobile("22-2").withWorkPhone("3(333)33").withEmail("test1@gmail.com")
                    .withEmail2("123@fgg.cv").withEmail3("34567")
                    .withByear("1990").withNotes("so many notes");
            app.contacts().create(contact);
            app.goTo().homePage();
            assertThat(app.contacts().count(), equalTo(before.size() + 1));
            Contacts after = app.contacts().all();
            assertThat(after, equalTo(
                    before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }
}