package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

/**
 * Created by IEUser on 7/31/2016.
 */
public class ApplicationManager {
    FirefoxDriver wd;
    private NavigationHelper navigationHelper;
    private GroupsHelper groupsHelper;
    private SessionHelper sessionHelper;



    public void init() {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/group.php");
        groupsHelper = new GroupsHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        sessionHelper = new SessionHelper(wd);
        sessionHelper.login("admin", "secret");
    }



    public void stop() {
       wd.quit();
    }

    public void fillContactForm(ContactData contactData) {
       wd.findElement(By.name("firstname")).click();
       wd.findElement(By.name("firstname")).clear();
       wd.findElement(By.name("firstname")).sendKeys(contactData.getFirstName());

       wd.findElement(By.name("lastname")).click();
       wd.findElement(By.name("lastname")).clear();
       wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());

       wd.findElement(By.name("nickname")).click();
       wd.findElement(By.name("nickname")).clear();
       wd.findElement(By.name("nickname")).sendKeys(contactData.getNick());

       wd.findElement(By.name("title")).click();
       wd.findElement(By.name("title")).clear();
       wd.findElement(By.name("title")).sendKeys(contactData.getTitle());

       wd.findElement(By.name("company")).click();
       wd.findElement(By.name("company")).clear();
       wd.findElement(By.name("company")).sendKeys(contactData.getCompany());

       wd.findElement(By.name("address")).click();
       wd.findElement(By.name("address")).clear();
       wd.findElement(By.name("address")).sendKeys(contactData.getAddress());

       wd.findElement(By.name("home")).click();
       wd.findElement(By.name("home")).clear();
       wd.findElement(By.name("home")).sendKeys(contactData.getPhone());

       wd.findElement(By.name("email")).click();
       wd.findElement(By.name("email")).clear();
       wd.findElement(By.name("email")).sendKeys(contactData.getEmail());

        //       addBirthday
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[1]")).isSelected()) {
           wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[1]")).click();
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[3]")).isSelected()) {
           wd.findElement(By.xpath("//div[@id='content']/form/select[1]//option[3]")).click();
        }
        if (!wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[2]")).isSelected()) {
           wd.findElement(By.xpath("//div[@id='content']/form/select[2]//option[2]")).click();
        }
       wd.findElement(By.name("byear")).click();
       wd.findElement(By.name("byear")).clear();
       wd.findElement(By.name("byear")).sendKeys(contactData.getByear());


//notes
       wd.findElement(By.name("notes")).click();
       wd.findElement(By.name("notes")).clear();
       wd.findElement(By.name("notes")).sendKeys(contactData.getNotes());
    }

    public void deleteSelectedContact() {
       wd.findElement(By.xpath("//div[@id='content']/form[2]/div[2]/input")).click();
    }
 //click popup button
    public void deleteSelectedContactPopup(){
       wd.switchTo().alert().accept();
    }

    public void selectFirstContact() {
        if (!wd.findElement(By.xpath("//*[@type='checkbox']")).isSelected()) {
           wd.findElement(By.xpath("//*[@type='checkbox']")).click();
        }
    }

    public void submitNewContact() {
       wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    public void createNewContact() {
       wd.findElement(By.linkText("add new")).click();
    }

    public GroupsHelper getGroupsHelper() {
        return groupsHelper;
    }

    public NavigationHelper getNavigationHelper() {
        return navigationHelper;
    }
}
