package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by IEUser on 7/31/2016.
 */
public class NavigationHelper extends  HelperBase{

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void groupPage() {
        if(!isElementPresent(By.tagName("h1"))
                || wd.findElement(By.tagName("h1")).getText().equals("Groups")
                || isElementPresent(By.name("new"))){
            return;
        }
        click(By.linkText("groups"));
 //      wd.findElement(By.linkText("groups")).click();
    }

    public void homePage() {
        if(isElementPresent(By.id("maintable"))){
            return;
        }
        click(By.linkText("home"));
    }

    public void goToEditPage() {
       if( !isElementPresent(By.tagName("h1"))
                || wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")
                ){
            return;
        }
        click(By.linkText("add new"));
    }

    public void homePageSelectedGroup(int id) {
        if (isElementPresent(By.id("maintable"))) {
            return;
        }
        click(By.cssSelector("a[href='./?group=" + id + "']"));
    }
}
