package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTest extends  TestBase{

    @DataProvider
    public Iterator<Object[]> validContacts(){
        List<Object[]> list = new ArrayList<Object[]>();
        list.add(new Object[] {new ContactData().withName("Name").withLastName("LastName").withAddress("adderss")
                                .withEmail("test@test.com").withPhone("1234567890")});
        list.add(new Object[] {new ContactData().withName("Name2").withLastName("LastName2").withAddress("adderss2")
                .withEmail("2test@test.com").withPhone("234567890")});
        list.add(new Object[] {new ContactData().withName("Name3").withLastName("LastName3").withAddress("adderss3")
                .withEmail("3test@test.com").withPhone("34567890")});
        return list.iterator();
    }

    @Test (dataProvider = "validContacts")
    public void contactCreationTest(ContactData contact) {

            app.goTo().homePage();
            Contacts before = app.contacts().all();
            File photo = new File("src/test/resources/stru.png");
//            ContactData contact = new ContactData().withName("Nata").withLastName("LastName")
////                    .withNick("nick").withTitle("tester").withCompany("company")
//                    .withAddress("Russia").withPhone("1234567890").withMobile("22-2").withWorkPhone("3(333)33").withEmail("test1@gmail.com")
//                    .withEmail2("123@fgg.cv").withEmail3("34567").withPhoto(photo)
////                    .withByear("1990").withNotes("so many notes")
//            ;
            app.contacts().create(contact);
            app.goTo().homePage();
            assertThat(app.contacts().count(), equalTo(before.size() + 1));
            Contacts after = app.contacts().all();
            assertThat(after, equalTo(
                    before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        }

//        @Test
//    public void testCurrentDir(){
//            File currentDir = new File(".");
//            System.out.println(currentDir.getAbsolutePath());
//            File photo = new File("src/test/resources/stru.png");
//            System.out.println(photo.getAbsolutePath());
//            System.out.println(photo.exists());
//        }
}