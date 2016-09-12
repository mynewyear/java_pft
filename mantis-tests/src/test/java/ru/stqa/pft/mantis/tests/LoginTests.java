package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import java.io.IOException;
import static org.testng.Assert.assertTrue;
/**
 * Created by IEUser on 9/9/2016.
 */
public class LoginTests extends TestBase {

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @AfterMethod(alwaysRun = true)
    public void stopMalServer(){
        app.mail().stop();
    }

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));
    }

}
