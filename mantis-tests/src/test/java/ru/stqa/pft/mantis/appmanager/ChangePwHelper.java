package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ChangePwHelper extends HelperBase {

  public ChangePwHelper(ApplicationManager app) {
    super(app);
  }

  public void selectUserById(int id) {
    click(By.cssSelector("a[href='manage_user_edit_page.php?user_id=" + id + "']"));
  }

  public void resetPassword() {
    click(By.xpath("//*[@id=\"manage-user-reset-form\"]"));
  }

}
