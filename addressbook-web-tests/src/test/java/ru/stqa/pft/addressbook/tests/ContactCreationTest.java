package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class ContactCreationTest extends  TestBase{


    @Test
    public void contactCreationTest() {
        //count before
 //       int before = app.getContactHelper().getContactCount();
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getNavigationHelper().goToEditPage();
        app.getContactHelper().createContact(new ContactData("Nata", "LastName", "Nata",
                "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                "so many notes", "test3"));

        app.getNavigationHelper().goToHomePage();
        //count
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() + 1 );
    }

}
