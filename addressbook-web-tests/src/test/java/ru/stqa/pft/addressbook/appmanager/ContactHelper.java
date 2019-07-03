package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {

    super(wd);
  }

  public void returnToHomePage() {
    click(By.linkText("home"));
  }

  public void initContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getHomephone());
    type(By.name("mobile"), contactData.getMobilephone());
    type(By.name("work"), contactData.getWorkphone());
    type(By.name("email"), contactData.getEmail1());
    type(By.name("email2"), contactData.getEmail2());
    click(By.name("bday"));
    new Select(wd.findElement(By.name("bday"))).selectByVisibleText("1");
    click(By.xpath("//div[@id='content']/form/select/option[3]"));
    click(By.name("bmonth"));
    new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText("January");
    click(By.xpath("//div[@id='content']/form/select[2]/option[2]"));
    type(By.name("byear"), contactData.getBirthdayyear());
    click(By.name("theform"));
  }
}
