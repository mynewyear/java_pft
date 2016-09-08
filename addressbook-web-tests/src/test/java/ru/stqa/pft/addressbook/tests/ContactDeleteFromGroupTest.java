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
 * Created by IEUser on 9/7/2016.
 */
public class ContactDeleteFromGroupTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        GroupData groups;
        if(app.db().groups().size() == 0){
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test3"));
        }

        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
           // File photo = new File("src/test/resources/stru.png");
            ContactData contact = new ContactData().withName("Nata").withLastName("LastName")
                    .withAddress("Russia").withPhone("1234567890").withMobile("22-2").withWorkPhone("3(333)33").withEmail("test1@gmail.com")
                    .withEmail2("123@fgg.cv").withEmail3("34567");
            app.contacts().create(contact);
            ContactData contactFromDB = app.db().contacts().iterator().next();
            GroupData group = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contacts().selectContactById(contactFromDB.getId());
            app.contacts().addToGroupById(group.getId());
            app.goTo().homePageSelectedGroup(group.getId());

        } else {
            ContactData contact = app.db().contacts().iterator().next();
            GroupData group = app.db().groups().iterator().next();
            app.goTo().homePage();
            app.contacts().selectContactById(contact.getId());
            app.contacts().addToGroupById(group.getId());
            app.goTo().homePageSelectedGroup(group.getId());
        }
    }

    @AfterMethod
    public  void allGroupVisible(){
        app.goTo().homePage();
        app.contacts().selectAllgroups();
    }

    @Test
    public void testDeleteFromGroup() {

        Contacts contacts = app.db().contacts();
        Iterator<ContactData> iteratorContacts = contacts.iterator();
        ContactData contact = iteratorContacts.next();
        GroupData group = contact.getGroups().iterator().next();

        app.goTo().homePage();

        while (iteratorContacts.hasNext()) {
            if (contact.getGroups().size() > 0) {
                group = contact.getGroups().iterator().next();
                app.contacts().filterGroupsById(group.getId());
                break;
            } else {
                contact = iteratorContacts.next();
            }
        }

        app.contacts().selectContactById(contact.getId());
        app.contacts().removeFromGroup();
        app.goTo().homePageSelectedGroup(group.getId());

        Groups groupsContactAfter = app.db().contactById(contact.getId()).iterator().next().getGroups();

        assertThat(groupsContactAfter, equalTo(
                contact.getGroups().withOut(group)));
    }
}

