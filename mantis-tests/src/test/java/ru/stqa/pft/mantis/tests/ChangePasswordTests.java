package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTests extends TestBase {

  @Test
  public void testChangePassword() throws MessagingException, IOException {
    String password = "password";
    String newPassword = "newpassword";
    app.login().start(app.getProperty("web.adminLogin"), app.getProperty("web.adminPassword"));
    app.goTo().manageOverviewPage();
    app.goTo().manageUserPage();
    Users users = app.db().users();
    UserData user = users.iterator().next();
    app.james().drainEmail(user.getUsername(), password); // очистка почтового ящика, чтобы находилась нужная ссылка для сброса
    app.change().selectUserById(user.getId());
    app.change().resetPassword();
    app.login().logout();
    List<MailMessage> mailMessages = app.james().waitForMail(user.getUsername(), password, 60000);
    String resetLink = findResetLink(mailMessages, user.getEmail());
    app.change().changePw(resetLink, newPassword);
    HttpSession session = app.newSession();
    assertTrue(session.login(user.getUsername(), newPassword));
    assertTrue(session.isLoggedInAs(user.getUsername()));
  }

  private String findResetLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }


}
