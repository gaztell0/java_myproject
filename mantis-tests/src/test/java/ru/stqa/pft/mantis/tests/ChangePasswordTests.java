package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.util.List;

public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePassword() throws MessagingException {
    String password = "password";
    app.login().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().manageOverviewPage();
    app.goTo().manageUserPage();
    Users users = app.db().users();
    UserData user = users.iterator().next();
    app.change().selectUserById(user.getId());
    app.change().resetPassword();
    app.login().logout();
    List<MailMessage> mailMessages = app.james().waitForMail(user.getUsername(), password, 60000);
    
  }

}
