package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Iterator;

/**
 * Created by IEUser on 9/3/2016.
 */
public class ContactGroupAddedTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            File photo = new File("src/test/resources/stru.png");
            app.contacts().create(new ContactData().withName("Nata").withLastName("LastName")
                    .withAddress("World").withPhoto(photo));
        }
    }

    @Test
    public void testAddToGroup() {
        Groups groupsBefore = app.db().groups();
        Contacts before = app.db().contacts();
        ContactData selectedContact = before.iterator().next();

        Groups contactsGroups = selectedContact.getGroups();
        GroupData selectedGroup;
        Iterator<ContactData> iteratorContacts = before.iterator();

        while (iteratorContacts.hasNext()) {
            if (contactsGroups.equals(groupsBefore)) {
                selectedContact = iteratorContacts.next();
                contactsGroups = selectedContact.getGroups();
            } else {
                break;
            }
        }
        if (contactsGroups.equals(groupsBefore)) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("18_test"));
        }

        app.goTo().homePage();
        app.contacts().selectContactById(selectedContact.getId());

    }
}

