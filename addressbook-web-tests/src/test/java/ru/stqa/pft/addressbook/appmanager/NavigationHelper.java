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

    public void goToGroupPage() {
        click(By.linkText("groups"));
 //      wd.findElement(By.linkText("groups")).click();
    }

    public void goToHomePage() {
        click(By.linkText("home"));
//       wd.findElement(By.linkText("home")).click();
    }
}
