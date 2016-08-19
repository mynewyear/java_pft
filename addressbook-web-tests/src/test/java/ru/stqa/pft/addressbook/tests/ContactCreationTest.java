package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends  TestBase{


    @Test
    public void contactCreationTest() {
        //count before
        List<ContactData> before = app.contact().list();


        app.goTo().goToEditPage();
        ContactData contact = new ContactData().withName("Nata").withLastName("LastName")
                .withNick("nick").withTitle("tester").withCompany("company")
                .withAddress("Russia").withPhone("1234567890").withEmail("test1@gmail.com")
                .withByear("1990").withNotes("so many notes").withGroup("test1");

        app.contact().create(contact);

        //count
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() + 1 );

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}