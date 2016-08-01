package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by IEUser on 7/31/2016.
 */
public class NavigationHelper {
    private FirefoxDriver wd;

    public NavigationHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    public void goToGroupPage() {
       wd.findElement(By.linkText("groups")).click();
    }

    public void goToHomePage() {
       wd.findElement(By.linkText("home")).click();
    }
}
