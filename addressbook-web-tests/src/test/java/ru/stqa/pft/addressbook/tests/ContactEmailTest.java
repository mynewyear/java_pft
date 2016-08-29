package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by IEUser on 8/28/2016.
 */
public class ContactEmailTest extends  TestBase{

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