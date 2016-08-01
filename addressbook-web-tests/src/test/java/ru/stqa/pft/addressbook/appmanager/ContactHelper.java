package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by IEUser on 8/1/2016.
 */
public class ContactHelper extends HelperBase{


    public ContactHelper(FirefoxDriver wd) {
        super(wd);

    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"),contactData.getFirstName());

       type(By.name("lastname"),contactData.getLastName());

       type(By.name("nickname"),contactData.getNick());

       type(By.name("title"),contactData.getTitle());

       type(By.name("company"),contactData.getCompany());

       type(By.name("address"),contactData.getAddress());

       type(By.name("home"),contactData.getPhone());

       type(By.name("email"),contactData.getEmail());

        //       addBirthday
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[1]")).isSelected()) {
           click(By.xpath("//div[@id='content']/form/select[1]//option[1]"));
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[3]")).isSelected()) {
           click(By.xpath("//div[@id='content']/form/select[1]//option[3]"));
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[2]")).isSelected()) {
           click(By.xpath("//div[@id='content']/form/select[2]//option[2]"));
        }
       type(By.name("byear"),contactData.getByear());


//notes
       type(By.name("notes"),contactData.getNotes());
    }

    public void deleteSelectedContact() {
       click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));
    }

    //click popup button
    public void deleteSelectedContactPopup(){
       wd.switchTo().alert().accept();
    }

    public void selectFirstContact() {
        if (!wd.findElement(By.xpath("//*[@type='checkbox']")).isSelected()) {
           click(By.xpath("//*[@type='checkbox']"));
        }
    }

    public void submitNewContact() {
       click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void createNewContact() {
       click(By.linkText("add new"));
    }

    public void editFirstContact() {
        click(By.xpath("//*[@id='maintable']/tbody/tr[2]/td[8]/a/img"));
    }

    public void updateContact() {
        click(By.name("update"));
    }
}
