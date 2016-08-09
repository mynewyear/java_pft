package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.List;

public class ContactDeletionTests extends TestBase{

    
    @Test
    public void contactDeletionTests() {
        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new ContactData("Nata", "LastName", "Nata",
                    "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                    "so many notes", "test3"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().selectContact(before.size() - 1);
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().deleteSelectedContactPopup();
        app.getNavigationHelper().goToHomePage();
        //count
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size() - 1 );
    }

}
