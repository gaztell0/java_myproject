package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }


  public void initContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());

    if (creation) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }

  public void initContactModificationById(int id) {
    wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
  }

  public void submitContactModification() {
    click(By.name("update"));
  }

  public void gotoAddnewPage() {
    click(By.xpath("//body"));
    click(By.linkText("add new"));
  }

  public void waitForMsgBox() {
    wd.findElement(By.cssSelector("div.msgbox"));
  }

  public void gotoHomePage() {
    if (isElementPresent(By.id("maintable"))){
      return;
    }
    click(By.linkText("home"));
  }

  public void create(ContactData contact, boolean b) {
    gotoAddnewPage();
    fillContactForm(contact, b);
    initContactCreation();
    contactCash = null;
    gotoHomePage();
  }

  public void modify(ContactData contact) {
    initContactModificationById(contact.getId());
    fillContactForm(contact, false);
    submitContactModification();
    contactCash = null;
    gotoHomePage();
  }

  public void delete(ContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContact();
    closeAlertWindow();
    waitForMsgBox();
    contactCash = null;
    gotoHomePage();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  private Contacts contactCash = null;

  public Contacts all() {
    if (contactCash != null) {
      return new Contacts(contactCash);
    }

    contactCash = new Contacts();
    List<WebElement> row = wd.findElements(By.name("entry"));
    List<WebElement> cellsLasttname = wd.findElements(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr/td[2]"));
    List<WebElement> cellsFirstname = wd.findElements(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr/td[3]"));
    List<WebElement> cellsAddress = wd.findElements(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr/td[4]"));
    List<WebElement> cellsEmail = wd.findElements(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr/td[5]"));
    List<WebElement> cellsAllPhones = wd.findElements(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr/td[6]"));
    for (int i = 0; i < row.size() ; i++) {
      String firstname =  cellsFirstname.get(i).getText();
      String lastname = cellsLasttname.get(i).getText();
      String address = cellsAddress.get(i).getText();
      String email = cellsEmail.get(i).getText();
      String allPhones = cellsAllPhones.get(i).getText();
      String[] phones = allPhones.split("\n");
      int id = Integer.parseInt(row.get(i).findElement(By.tagName("input")).getAttribute("value"));
      contactCash.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname)
              .withHomeAddress(address).withHomePhone(phones[0]).withMobilePhone(phones[1])
              .withWorkPhone(phones[2]).withEmail(email));
    }
    return new Contacts(contactCash);
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String home = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    wd.navigate().back();
    return new ContactData().withId(contact.getId()).withFirstname(firstname).withLastname(lastname)
            .withHomeAddress(address).withHomePhone(home).withMobilePhone(mobile).withWorkPhone(work).withEmail(email);


  }

}