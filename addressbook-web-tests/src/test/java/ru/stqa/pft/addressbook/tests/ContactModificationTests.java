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
         app.contact().create(new ContactData("Nata", "LastName", "Nata",
                 "tester", "company", "Russia", "1234567890", "test1@gmail.com", "1990",
                 "so many notes", "test3"));
      }
   }

    @Test
     public void contactModificationTests() {
         //count before test
        List<ContactData> before = app.contact().list();

       int index = before.size() - 1;
       ContactData contact = new ContactData(before.get(index).getId(), "firstName1", "newLastName",
               "new nick", "newTitle", "newCompany", "world", "0987654321", "newEmail@test.com", "1989",
               "more notes than last time",null);

       app.contact().modify(index, contact);

        //count
        List<ContactData> after = app.contact().list();
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
