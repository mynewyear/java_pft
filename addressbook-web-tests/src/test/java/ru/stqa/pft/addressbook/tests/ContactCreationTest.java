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
        ContactData contact = new ContactData("Nata", "LastName", "nik",
                "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                "so many notes", "test1");
        app.contact().create(contact);
 //       app.goTo().homePage();
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