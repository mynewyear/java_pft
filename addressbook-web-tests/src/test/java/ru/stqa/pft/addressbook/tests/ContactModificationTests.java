package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
     public void contactModificationTests() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().editFirstContact();
        app.getContactHelper().fillContactForm(new ContactData("firstName","newLastName",
                "new nick", "newTitle", "newCompany", "world", "0987654321", "newEmail@test.com", "1989",
                "more notes than last time"));
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
    }
}
