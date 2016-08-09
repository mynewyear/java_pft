package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
     public void contactModificationTests() {
        app.getNavigationHelper().goToHomePage();
        //count before
        int before = app.getContactHelper().getContactCount();

        if(!app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new ContactData("Nata", "LastName", "Nata",
                    "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                    "so many notes", "test3"));

        }
//        app.getContactHelper().editFirstContact();
        app.getContactHelper().selectEditContact(before - 1);
        app.getContactHelper().fillContactForm(new ContactData("firstName",null,
                "new nick", "newTitle", "newCompany", "world", "0987654321", "newEmail@test.com", "1989",
                "more notes than last time",null),false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        //count
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before );
    }
}
