package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDataTest extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().homePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData().withName("Nata").withLastName("LastName")
                    .withNick("nick").withTitle("tester").withCompany("company")
                    .withAddress("Russia").withPhone("1234567890").withMobile("123").withEmail("test1@gmail.com")
                    .withEmail2("1w@w.ri").withByear("1990").withNotes("so many notes"));
        }
    }

    @Test
    public void contactEmailTest() {

        app.goTo().homePage();
        ContactData contact = app.contacts().all().iterator().next();

        ContactData contactInfoFromImageForm = app.contacts().infoContactInfoPage(contact);
        ContactData contactInfoFromEditForm = app.contacts().infoFromEditForm(contact);
        System.out.println(contactInfoFromImageForm);
        assertThat(contactInfoFromImageForm.getAllInformation(),
                equalTo(mergeAllInformation(contactInfoFromEditForm)));
           }

    private String mergeAllInformation(ContactData contact) {
        return null;
    }

    public static String cleaned(String phone){
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

//    private  String mergeEmails(ContactData contact){
//        return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3())
//                .stream().filter((s) -> ! s.equals(""))
//                .collect(Collectors.joining("\n"));
//    }
}
