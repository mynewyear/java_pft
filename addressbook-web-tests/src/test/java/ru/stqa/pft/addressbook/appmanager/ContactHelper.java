package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactHelper extends HelperBase {



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

        if (creation) {
            new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        } else {
            Assert.assertFalse(isElementPresent(By.className("new_group")));
        }
    }


    public void deleteSelectedContact() {
       click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    //click popup button
    public void deleteSelectedContactPopup(){
        alertAccept();
    }



    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
//        if (!elementSelected(By.xpath("//*[@type='checkbox']"))) {
//           click(By.xpath("//*[@type='checkbox']"));
//        }
    }

    public void selectEditContact(int index) {
        wd.findElements(By.xpath("//td[8]/a/img")).get(index).click();
    }

    public void submitNewContact() {
       click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactCreation() {
       click(By.linkText("add new"));
    }

    public void editFirstContact() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void updateContact() {
        click(By.name("update"));
    }

    public void createContact(ContactData contactData) {
        initContactCreation();
        fillContactForm(contactData, true);
        submitNewContact();
    }

    public boolean isThereContact() {
        return isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public int getContactCount() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public List<ContactData> getContactList() {
        List<ContactData> groups = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement element : elements){
            String name = element.getText();
            ContactData group = new ContactData("Name",null,null,null,null,null,null,null,null,null,null);
            groups.add(group);

        }
        return groups;
    }
}
