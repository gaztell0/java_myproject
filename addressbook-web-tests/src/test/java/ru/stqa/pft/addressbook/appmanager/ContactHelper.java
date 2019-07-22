package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.ArrayList;
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

  public void selectContact(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void deleteSelectedContact() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void closeAlertWindow() {
    wd.switchTo().alert().accept();
  }

  public void initContactModification(int index) {
    wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
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
    gotoHomePage();
  }

  public void modify(int index, ContactData contact) {
    initContactModification(index);
    fillContactForm(contact, false);
    submitContactModification();
    gotoHomePage();
  }

  public void delete(int index) {
    selectContact(index);
    deleteSelectedContact();
    closeAlertWindow();
    waitForMsgBox();
    gotoHomePage();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<ContactData> list() {

    List<ContactData> contacts = new ArrayList<ContactData>();

    List<WebElement> row = wd.findElements(By.name("entry"));
    List<WebElement> cellsLasttname = wd.findElements(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr/td[2]"));
    List<WebElement> cellsFirstname = wd.findElements(By.xpath("/html/body/div/div[4]/form[2]/table/tbody/tr/td[3]"));
    for (int i = 0; i < row.size() ; i++) {
      String firstname =  cellsFirstname.get(i).getText();
      String lastname = cellsLasttname.get(i).getText();
      int id = Integer.parseInt(row.get(i).findElement(By.tagName("input")).getAttribute("value"));
      contacts.add(new ContactData().withId(id).withFirstname(firstname).withLastname(lastname));
    }
    return contacts;
  }
}