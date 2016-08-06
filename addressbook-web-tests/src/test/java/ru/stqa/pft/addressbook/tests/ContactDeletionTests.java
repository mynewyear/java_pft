package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

public class ContactDeletionTests extends TestBase{

    
    @Test
    public void contactDeletionTests() {
        app.getNavigationHelper().goToHomePage();
        //count before
        int before = app.getContactHelper().getContactCount();

        if(!app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new ContactData("Nata", "LastName", "Nata",
                    "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                    "so many notes", "test3"), true);

        }
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().deleteSelectedContactPopup();
        app.getNavigationHelper().goToHomePage();
        //count
        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1 );
    }

}
