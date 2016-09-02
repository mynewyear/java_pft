package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTest extends  TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if(app.db().contacts().size() == 0){
            app.goTo().homePage();
            app.contacts().create(new ContactData().withName("Nata").withLastName("LastName")
                    .withAddress("Russia").withPhone("1234567890").withMobile("22-2").withWorkPhone("3(333)33").withEmail("test1@gmail.com")
                    .withEmail2("123@fgg.cv").withEmail3("34567"));
        }
    }

        @Test
        public void contactEmailTest() {
            app.goTo().homePage();
            ContactData contact = app.contacts().all().iterator().next();
            ContactData contactInfoEditForm = app.contacts().infoFromEditForm(contact);

            assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoEditForm)));
        }
            private  String mergeEmails(ContactData contact){
                return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
                        .stream().filter((s) -> ! s.equals(""))
                        .collect(Collectors.joining("\n"));
        }

    }