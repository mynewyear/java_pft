package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Created by IEUser on 9/9/2016.
 */
public class RegistrationHelper extends HelperBase{

    public RegistrationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.cssSelector("#email-field"), email);
        click(By.cssSelector("input.button"));
    }

    public void finish(String confirmationLink, String password) {
        wd.get(confirmationLink);
        type(By.cssSelector("#password"), password);
        type(By.cssSelector("#password-confirm"), password);
        click(By.cssSelector("input.button"));
    }
}