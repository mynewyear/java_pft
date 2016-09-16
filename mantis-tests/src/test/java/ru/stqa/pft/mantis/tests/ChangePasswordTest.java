package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by IEUser on 9/12/2016.
 */
public class ChangePasswordTest extends  TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }


    @Test
    public void changePasswordTest() throws IOException, MessagingException {
//        HttpSession session = app.newSession();
//        assertTrue(session.login("administrator", "root"));
//        assertTrue(session.isLoggedInAs("administrator"));

        String newPassword = "password";
        UserData user = app.db().users().iterator().next();
        app.admin().resetUserPassword(user.getUsername());
        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String resetPasswordLink = app.user().findResetPasswordLink(mailMessages, user.getEmail());
        app.user().resetPassword(user.getUsername(), resetPasswordLink, newPassword);
        Assert.assertTrue(app.newSession().login(user.getUsername(), newPassword));
    }


    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
