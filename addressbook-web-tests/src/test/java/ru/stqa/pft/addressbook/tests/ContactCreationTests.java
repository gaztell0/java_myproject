package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoAddnewPage();
    app.getContactHelper().fillContactForm(new ContactData("Михаил", "Петрович", "Жуков", "Bug", "ADT Inc.", "Россия, г. Москва, ул. Аргуновская 35", "84957757575", "89262881045", "+74951231212", "ivanmercury@yandex.ru", "ivanmercury@gmail.com", "1989", "test1"), true);
    app.getContactHelper().initContactCreation();
    app.getNavigationHelper().gotoHomePage();
    app.getSessionHelper().logout();
  }

}
