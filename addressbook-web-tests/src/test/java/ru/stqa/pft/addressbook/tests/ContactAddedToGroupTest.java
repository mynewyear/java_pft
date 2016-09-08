package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.File;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by IEUser on 9/3/2016.
 */
public class ContactAddedToGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
           // File photo = new File("src/test/resources/stru.png");
            app.contacts().create(new ContactData().withName("Nata").withLastName("LastName")
                    .withAddress("Russia").withPhone("1234567890").withMobile("22-2").withWorkPhone("3(333)33").withEmail("test1@gmail.com")
                    .withEmail2("123@fgg.cv").withEmail3("34567"));
        }
    }

    @AfterMethod
    public  void allGroupVisible(){
        app.goTo().homePage();
        app.contacts().selectAllgroups();
    }

    @Test
    public void testAddToGroup() {

        Groups groupsBefore = app.db().groups();
        Contacts contactsBefore = app.db().contacts();

        ContactData selectedContact = contactsBefore.iterator().next();
        Groups groupsSelectedContact = selectedContact.getGroups();

        GroupData selectedGroup;
        Iterator<ContactData> iteratorContacts = contactsBefore.iterator();

        while (iteratorContacts.hasNext()) {
            if (groupsSelectedContact.equals(groupsBefore)) {
                selectedContact = iteratorContacts.next();
                groupsSelectedContact = selectedContact.getGroups();
            } else {
                break;
            }
        }
        if (groupsSelectedContact.equals(groupsBefore)) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test 0"));
        }
        groupsBefore = app.db().groups();
        groupsSelectedContact = selectedContact.getGroups();
        groupsBefore.removeAll(groupsSelectedContact);

        if (groupsBefore.size() > 0) {
            selectedGroup = groupsBefore.iterator().next();
        } else {
            throw new RuntimeException("not available");
        }
        app.goTo().homePage();
        app.contacts().selectContactById(selectedContact.getId());
        app.contacts().addToGroupById(selectedGroup.getId());
        app.goTo().homePageSelectedGroup(selectedGroup.getId());

        ContactData after = app.db().contactById(selectedContact.getId()).iterator().next();
        Groups groupsContactAfter = after.getGroups();

        assertThat(groupsContactAfter, equalTo(
                groupsSelectedContact.withAdded(selectedGroup)));

    }


}


