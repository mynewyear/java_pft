package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactModificationTests extends TestBase {
    @Test
     public void contactModificationTests() {
        app.getNavigationHelper().goToHomePage();
        if(!app.getContactHelper().isThereContact()){
            app.getContactHelper().createContact(new ContactData("Nata", "LastName", "Nata",
                    "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                    "so many notes", "test3"));
        }
        List<ContactData> before = app.getContactHelper().getContactList();
        app.getContactHelper().initContractModification(before.size() - 1);
        ContactData contact = new ContactData(before.get(before.size() - 1).getId(), "firstName1", "newLastName",
                "new nick", "newTitle", "newCompany", "world", "0987654321", "newEmail@test.com", "1989",
                "more notes than last time",null);
        app.getContactHelper().fillContactForm(contact, false);
        app.getContactHelper().updateContact();
        app.getNavigationHelper().goToHomePage();
        //count
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), before.size());

        before.remove(before.size() -1 );
        before.add(contact);
 //       Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }

}
