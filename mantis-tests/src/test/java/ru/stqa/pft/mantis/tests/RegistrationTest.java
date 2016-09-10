package ru.stqa.pft.mantis.tests;


import org.testng.annotations.Test;

/**
 * Created by IEUser on 9/9/2016.
 */
public class RegistrationTest extends TestBase {

    @Test
    public void testRegistration() {
                app.registration().start(" user1", "user1@localhost.localdomain");
    }
}