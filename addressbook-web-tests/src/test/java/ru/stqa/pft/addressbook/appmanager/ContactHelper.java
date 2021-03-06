package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

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
        type(By.name("mobile"), contactData.getMobile());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmail());
        type(By.name("email2"), contactData.getEmail2());
        type(By.name("email3"), contactData.getEmail3());
        attach(By.name("photo"), contactData.getPhoto());

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
            if (contactData.getGroups().size() > 0) {
                Assert.assertTrue(contactData.getGroups().size() == 1);
                new Select(wd.findElement(By.name("new_group")))
                        .selectByVisibleText(contactData.getGroups().iterator().next().getName());
            }
        } else {
            Assert.assertFalse(isElementPresent(By.className("new_group")));
        }
    }

    public void deleteSelectedContact() {
        click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    //click popup button
    public void deleteSelectedContactPopup() {
        alertAccept();
    }

    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[id ='" + id + "']")).click();
    }

//    public void initContactModification(int index) {
//        wd.findElements(By.xpath("//td[8]/a/img")).get(index).click();
//    }

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
        contactCashe = null;
    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        deleteSelectedContactPopup();
        contactCashe = null;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        updateContact();
        contactCashe = null;
    }

    public boolean isThereContact() {
        return isElementPresent(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    private Contacts contactCashe = null;

    public Contacts all() {
        if (contactCashe != null) {
            return new Contacts(contactCashe);
        } else {
            contactCashe = new Contacts();
            List<WebElement> rows = wd.findElements(By.name("entry"));
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                int id = Integer.parseInt(row.findElement(By.name("selected[]")).getAttribute("id"));
                String lastName = cells.get(1).getText();
                String firstName = cells.get(2).getText();
                String address = cells.get(3).getText();
                String allEmails = cells.get(4).getText();
                String allPhones = cells.get(5).getText();
                contactCashe.add(new ContactData().withId(id).withName(firstName).withLastName(lastName)
                        .withAllPhones(allPhones)
                        .withAllEmails(allEmails)
                        .withAddress(address)
                );
            }
            return new Contacts(contactCashe);
        }
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());
        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String home = wd.findElement(By.name("home")).getAttribute("value");
        String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String email = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstName)
                .withLastName(lastName).withPhone(home).withMobile(mobile).withWorkPhone(workPhone)
                .withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
    }

    public ContactData infoFromContactDetailsPage(ContactData contact) {
        clickImageContactById(contact.getId());
        String allInformation = wd.findElement(By.id("content")).getText();
        String firstName = allInformation.split("\\s")[0];
        String lastName = allInformation.split("\\s")[1];
        String address = allInformation.split("\\n")[1];
        String phoneHome = allInformation.split("\\n")[3].replace("H: ", "");
        String phoneMobile = allInformation.split("\\n")[4].replace("M: ", "");
        String phoneWork = allInformation.split("\\n")[5].replace("W: ", "");
        String email = wd.findElement(By.xpath("//*[@id='content']/a[1]")).getText();
        String email2 = wd.findElement(By.xpath("//*[@id='content']/a[3]")).getText();
        String email3 = wd.findElement(By.xpath("//*[@id='content']/a[5]")).getText();

        wd.navigate().back();
        return new ContactData().withId(contact.getId()).withName(firstName).withLastName(lastName).withAddress(address)
                .withPhone(phoneHome).withMobile(phoneMobile).withWorkPhone(phoneWork)
                .withEmail(email).withEmail2(email2).withEmail3(email3);
    }

    private void clickImageContactById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href ='view.php?id=%s']", id))).click();
    }


    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href ='edit.php?id=%s']", id))).click();
    }

    public void addToGroupById(int id) {
        click(By.cssSelector("select[name='to_group']"));
        click(By.cssSelector(".right>select>option[value='" + id + "']"));
        click(By.name("add"));
    }


    public void removeFromGroup() {
        click(By.cssSelector("input[name='remove']"));
    }

    public void filterGroupsById(int id) {
        click(By.cssSelector("#right"));
        click(By.cssSelector("#right>select>option[value='" + id + "']"));
    }

    public void selectAllgroups() {
        click(By.cssSelector("#right"));
        click(By.cssSelector("#right>select>option[value='[none]']"));

    }
}