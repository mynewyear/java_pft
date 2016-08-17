package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTest extends  TestBase{


    @Test
    public void contactCreationTest() {
        //count before
        //       int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.getContactHelper().getContactList();

        app.getNavigationHelper().goToEditPage();
//        int id = before.get(before.size()-1).getId();
        ContactData contact = new ContactData("Nata", "LastName", "nik",
                "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                "so many notes", "test3");
        app.getContactHelper().createContact(contact);
        app.getNavigationHelper().goToHomePage();

        //count
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1 );

        before.add(contact);
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}