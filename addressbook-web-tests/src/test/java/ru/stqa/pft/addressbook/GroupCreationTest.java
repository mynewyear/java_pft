package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTest extends TestBase {


    @Test
    public void testGroupCreation() {
        goToGroupPage();
        initGroupCreation();
        fillGoupForm(new GroupData("test1", "test2", "test3"));
        submitGoupCreation();
        returnToGroupPage();
    }

}
