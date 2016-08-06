package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends  TestBase{


    @Test
    public void contactCreationTest() {
        //count before
        int before = app.getContactHelper().getContactCount();
        app.getNavigationHelper().goToEditPage();
        app.getContactHelper().createContact(new ContactData("Nata", "LastName", "Nata",
                "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                "so many notes", "test3"), true);

        app.getNavigationHelper().goToHomePage();
        //count
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before + 1 );
    }

}
