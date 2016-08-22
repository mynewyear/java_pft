package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTest extends  TestBase{


    @Test
    public void contactCreationTest() {
        app.goTo().homePage();
        //count before
        Set<ContactData> before = app.contacts().all();


 //       app.goTo().goToEditPage();
        ContactData contact = new ContactData().withName("Nattocoyva").withLastName("LastName")
                .withNick("nick").withTitle("tester").withCompany("company")
                .withAddress("Russia").withPhone("1234567890").withEmail("test1@gmail.com")
                .withByear("1990").withNotes("so many notes");

        app.contacts().create(contact);

        //count
        Set<ContactData> after = app.contacts().all();
        Assert.assertEquals(after.size(), before.size() + 1 );

        contact.withId(after.stream().mapToInt((c) -> c.getId()).max().getAsInt());
        before.add(contact);
        Assert.assertEquals(before, after);
    }

}