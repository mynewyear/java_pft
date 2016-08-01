package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeletionTests extends TestBase{

    
    @Test
    public void contactDeletionTests() {
        app.getNavigationHelper().goToHomePage();
        app.selectFirstContact();
        app.deleteSelectedContact();
        app.deleteSelectedContactPopup();
        app.getNavigationHelper().goToHomePage();
    }

}
