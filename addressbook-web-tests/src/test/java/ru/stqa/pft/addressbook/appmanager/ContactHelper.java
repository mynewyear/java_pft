package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactHelper extends HelperBase {

    public int count() {
        return wd.findElements(By.name("entry")).size();
    }

    public ContactHelper(WebDriver wd) {
        super(wd);

    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());

        type(By.name("lastname"), contactData.getLastName());

        type(By.name("nickname"), contactData.getNick());

        type(By.name("title"), contactData.getTitle());

        type(By.name("company"), contactData.getCompany());

        type(By.name("address"), contactData.getAddress());

        type(By.name("home"), contactData.getPhone());

        type(By.name("email"), contactData.getEmail());

        //       addBirthday
        if (!elementSelected(By.xpath("//div[@id='content']/form/select[1]//option[1]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[1]"));
        }
        if (!elementSelected(By.xpath("//div[@id='content']/form/select[1]//option[3]"))) {
            click(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
        }
        if (!elementSelected(By.xpath("//div[@id='content']/form/select[2]//option[2]"))) {
            click(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
        }
        //BthDay
        type(By.name("byear"), contactData.getByear());
//notes
        type(By.name("notes"), contactData.getNotes());
//
//        if (creation) {
//            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
//        } else {
//            Assert.assertFalse(isElementPresent(By.className("new_group")));
//        }
    }

    public void goHomePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    //click popup button
    public void deleteSelectedContactPopup(){
        alertAccept();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();

    }
//
//    public void initContactModification(int index) {
//        wd.findElements(By.xpath("//td[8]/a/img")).get(index).click();
//    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href ='edit.php?id=%s']", id))).click();
    }

    public void submitNewContact() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void create(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitNewContact();
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        deleteSelectedContactPopup();
        goHomePage();
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        updateContact();
    }

    public boolean isThereContact() {
        return isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public Contacts contacts = null;

    public Contacts all() {
        if (contacts != null) {
            return new Contacts(contacts);
        } else {
            contacts = new Contacts();
            List<WebElement> rows = wd.findElements(By.name("entry"));

            int i = 2;
            for (WebElement element : rows) {

                int id = Integer.parseInt(element.findElement(By.name("selected[]")).getAttribute("id"));
                String firstName = element.findElement(By.xpath("//*[@id='maintable']/tbody/tr[" + i + "]/td[3]")).getText();
                String lastName = element.findElement(By.xpath("//*[@id='maintable']/tbody/tr[" + i + "]/td[2]")).getText();
                String address = element.findElement(By.xpath("//*[@id='maintable']/tbody/tr[" + i + "]/td[4]")).getText();

//                ContactData contact = new ContactData().withId(id).withName(firstName).withLastName(lastName).withAddress(address);
                contacts.add(new ContactData().withId(id).withName(firstName).withLastName(lastName).withAddress(address));
                i++;
            }
            return contacts;
        }
    }
}