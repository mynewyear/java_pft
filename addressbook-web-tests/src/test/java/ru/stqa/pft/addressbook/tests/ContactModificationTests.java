package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

   @BeforeMethod
   public void ensurePreconditions() {
      app.goTo().homePage();
      if (app.contact().list().size() == 0) {
         app.contact().create(new ContactData().withName("Nata").withLastName("LastName")
                 .withNick("nick").withTitle("tester").withCompany("company")
                 .withAddress("Russia").withPhone("1234567890").withEmail("test1@gmail.com")
                 .withByear("1990").withNotes("so many notes").withGroup("test1"));
      }
   }

    @Test
     public void contactModificationTests() {
         //count before test
        List<ContactData> before = app.contact().list();

       int index = before.size() - 1;
       ContactData contact = new ContactData().withId(before.get(index).getId()).withName("NataLia")
               .withLastName("LastName").withAddress("world");

       app.contact().modify(index, contact);

        //count
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
 //       Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
