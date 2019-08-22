package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class LoginHelper extends HelperBase {

  public LoginHelper (ApplicationManager app) {
    super(app);
  }

  public void start(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    wd.findElement(By.id("login-form")).submit();
    type(By.name("password"), password);
    wd.findElement(By.id("login-form")).submit();
  }

}
