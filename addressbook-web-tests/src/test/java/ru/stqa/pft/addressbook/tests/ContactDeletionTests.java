package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    
    @Test
    public void contactDeletionTests() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().selectFirstContact();
        app.getContactHelper().deleteSelectedContact();
        app.getContactHelper().deleteSelectedContactPopup();
        app.getNavigationHelper().goToHomePage();
    }

}
