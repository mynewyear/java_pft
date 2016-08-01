package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTest extends  TestBase{


    @Test
    public void contactCreationTest() {

        app.createNewContact();
        app.fillContactForm(new ContactData("Nata", "LastName", "Nata", "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990", "so many notes"));
        app.submitNewContact();
        app.getNavigationHelper().goToHomePage();
    }


}
