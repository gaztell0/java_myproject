package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(ApplicationManager app) {
    super(app);
  }

  public void manageOverviewPage() {
    click(By.cssSelector("a[href=\"/mantisbt-2.21.1/manage_overview_page.php\"]"));
  }

  public void manageUserPage() {
    click(By.cssSelector("a[href=\"/mantisbt-2.21.1/manage_user_page.php\"]"));
  }

}
