package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  WebDriver wd;

  private ContactHelper contactHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;

  public void init() {
    wd = new FirefoxDriver();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/");
    groupHelper = new GroupHelper(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    contactHelper = new ContactHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public void logout() {
    sessionHelper.logout();
  }

  public void returnToHomePage() {
    contactHelper.returnToHomePage();
  }

  public void initContactCreation() {
    contactHelper.initContactCreation();
  }

  public void fillContactForm(ContactData contactData) {
    contactHelper.fillContactForm(contactData);
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public void gotoAddnewPage() {
    navigationHelper.gotoAddnewPage();
  }
}
