package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModification extends TestBase {

  @Test
  public void testContactModification() {
    app.getNavigationHelper().gotoHomePage();
    app.getContactHelper().initContactModification();
    app.getContactHelper().fillContactForm(new ContactData("Петр", "Алексеевич",
                                                           "Кутузов", "Bug",
                                                           "ADT Inc.", "Россия, г. Москва, ул. Аргуновская 35",
                                                           "84957757575", "89262881045",
                                                           "+74951231212", "ivanmercury@yandex.ru",
                                                           "ivanmercury@gmail.com", "1989",
                                                           null), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }
}
