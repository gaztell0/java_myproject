package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;

public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePassword() {
    app.login().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().manageOverviewPage();
    app.goTo().manageUserPage();
  }

}
